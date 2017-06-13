package boot;

import java.util.List;

import Model.Actions.ConsoleLevelDisplayer;
import Model.Actions.LoadLevel;
import Model.Data.Level;
import planner.Planner;
import planner.Strips;
import sokobanAdapter.SokoPlaneable;
import sokobanAdapter.Util;
import sokobanAdapter.actions.SokoActionP;

public class Test {

	public static void main(String[] args) {
		
		LoadLevel lvlload = new LoadLevel();
		Level lvlInitial = new Level();
		
	//	lvlload.load(lvlInitial, args[0]);
		lvlload.load(lvlInitial, "level1.txt");
		
		ConsoleLevelDisplayer disp = new ConsoleLevelDisplayer();	
		disp.display(lvlInitial);
		
		long start = System.currentTimeMillis();
		List<actionP.Action> plan = getPlan(lvlInitial, 8);
		long end = System.currentTimeMillis();

		if (plan == null)
		{
			System.out.println("No solution");
			double time = end-start;
			System.out.println("\ntime: "+(time/1000)+"sec");	
		}
		else
		{
			int steps =0;
			for (actionP.Action ap : plan)
			{
				if (ap instanceof SokoActionP)
				{
					SokoActionP sap = (SokoActionP) ap;
					System.out.println(sap.getName()+":");			
					for (actionS.Action as : sap.getActionsS())
					{
						
						System.out.println(as.getAction());
						steps++;
					}
				}
			}
			double time = end-start;
			System.out.println("\ntime: "+(time/1000)+"sec");		
			System.out.println("steps: "+steps);					
		}	
		
	/*		
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
		*/		
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