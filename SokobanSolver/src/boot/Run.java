package boot;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import Model.Actions.LoadLevel;
import Model.Data.Level;
import planner.Planner;
import planner.Strips;
import sokobanAdapter.SokoPlaneable;
import sokobanAdapter.Util;
import sokobanAdapter.actions.SokoActionP;


public class Run {

	public static void main(String[] args) {

		LoadLevel lvlload = new LoadLevel();
		Level lvlInitial = new Level();
		
		lvlload.load(lvlInitial, args[0]);			
		List<actionP.Action> plan = getPlan(lvlInitial, 8);	
		try 
		{
			FileOutputStream out = new FileOutputStream(args[1]);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			
			if (plan == null)
				writer.write("No solution");
			else
				for (actionP.Action ap : plan)
					if (ap instanceof SokoActionP)
					{
						SokoActionP sap = (SokoActionP) ap;
						for (actionS.Action as : sap.getActionsS())
						{
							writer.write(as.getAction());
							writer.newLine();
						}
					}
			writer.close();	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static List<actionP.Action> getPlan (Level init, int x) {
		
		List<actionP.Action> plan = null;
		Level level;
		int numOfActions = Integer.MAX_VALUE;
		long start, end;
		int count;
			
		Planner planner = new Strips();	
			
		for (int i=0; i<x; i++)
		{
			level = Util.generateLevelFromCharArray(Util.generateCharArrayFromLevel(init));
			SokoPlaneable planeable = new SokoPlaneable(level);
			count = 0;
			
			start = System.currentTimeMillis();
			List<actionP.Action> actions = planner.plan(planeable);
			end = System.currentTimeMillis();
			
			if (actions != null && actions.size()>0)
			{
				for (actionP.Action ap : actions)
					if (ap instanceof SokoActionP)
					{
						SokoActionP sap = (SokoActionP) ap;
						count+= sap.getActionsS().size();
					}
				if (count < numOfActions)
				{
					plan = actions;
					numOfActions = count;
					if (((double)(end-start))/1000 > (60/x))
						return plan;
				}	
			}
		}
		return plan;
	}
}