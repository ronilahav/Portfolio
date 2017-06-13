package sokobanAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Model.Data.Level;
import Model.Data.Objects.Box;
import Model.Data.Objects.Destination;
import Model.Data.Objects.Floor;
import Model.Data.Objects.GraphicObject;
import Model.Data.Objects.GraphicObjectHolder;
import Model.Data.Objects.Player;
import Model.Data.Objects.Wall;
import Model.ObjectsCreator.ObjectHolderFactory;
import predicate.And;
import predicate.Predicate;
import sokobanAdapter.predicate.On;
import sokobanAdapter.predicate.SokoAnd;


public class Util {
	
	public static final String box="Box";
	public static final String clear="Clear";
	public static final String wall="Wall";
	public static final String player="Player";
	public static final String target="Target";
	
	public static Level generateLevelFromPredicate(And and){
		HashMap<Integer, HashMap<Integer, Character>> charLevel = new HashMap<>();
		
		int[] xy;
		for(Predicate on : and.getAllPredicate()){
			if(on instanceof On){
				xy = getXY(on.getValue());
				if(!charLevel.containsKey(xy[1]))
					charLevel.put(xy[1], new HashMap<Integer, Character>());
				charLevel.get(xy[1]).put(xy[0], ((On) on).getDisp());
			}
		}
		
		char[][] charArray = new char[charLevel.keySet().size()][];
		String line="";
		for (Integer yKey : charLevel.keySet()){
			for(Integer xKey : charLevel.get(yKey).keySet())
				line+=charLevel.get(yKey).get(xKey);
			charArray[yKey] = line.toCharArray();
			line = "";
		}
		return generateLevelFromCharArray(charArray);
	}
	
	
	public static int[] getXY(String s){
		//String n = s.subSequence(1, (s.length()-1) ).toString();
		String[] x = s.split(",");
		int[] a = { Integer.parseInt(x[0]) ,  Integer.parseInt(x[1])}; 
		return a;
		}
	
	public static And generatePredicateFromLevel(Level lvl) {
		And a = new SokoAnd();
		HashMap<Character, String> typeNames = new HashMap<>();
		typeNames.put('o', "Target");
		typeNames.put(' ', "Clear");
		typeNames.put('@', "Box");
		typeNames.put('$', "Box");
		typeNames.put('A', "Player");
		typeNames.put('a', "Player");
		typeNames.put('#', "Wall");
		for (ArrayList<GraphicObjectHolder> arrLine : lvl.getArr()) {
			for (GraphicObjectHolder h : arrLine) {
				if (h.getObj() instanceof Floor || h.getObj() instanceof Wall || h.getObj() instanceof Destination || h.getObj() instanceof Player)
					a.addPredicate(new On(typeNames.get(h.getDispChar()), "", h.getObj().getPos(),h.getDispChar()));
				else
					a.addPredicate(new On(typeNames.get(h.getDispChar()) + "", h.getObj().hashCode() + "", h.getObj().getPos(),h.getDispChar()));

			} // End of for loop.
		}
		return a;
	}

	public static Level generateLevelFromCharArray(char[][] charLevel) {
		Level lvl = new Level();
		ArrayList<ArrayList<GraphicObjectHolder>> arr = new ArrayList<ArrayList<GraphicObjectHolder>>();
		StringBuilder line = new StringBuilder();
		int lineNumber = 0;
		for (char[] cArr : charLevel) {
			line.delete(0, line.length());
			for (char c : cArr)
				line.append(c);
			arr.add(readLineToArrayList(line.toString(), lineNumber, lvl));
			lineNumber++;
		}
		lvl.setArr(arr);
		return lvl;
	}

	public static char[][] generateCharArrayFromLevel(Level lvl) {
		StringBuilder sb = new StringBuilder();
		StringBuffer lineBuilder = new StringBuffer();
		String rowDown = "\r\n";
		int x = 0;
		char[][] a = new char[lvl.getArr().size()][];

		for (ArrayList<GraphicObjectHolder> arrLine : lvl.getArr()) {
			lineBuilder.delete(0, lineBuilder.length());
			for (GraphicObjectHolder h : arrLine) {
				sb.append(h.getDispChar());
				lineBuilder.append(h.getDispChar());
			} // End of for loop.

			a[x++] = lineBuilder.toString().toCharArray();
			sb.append(rowDown);
		}
		return a;
	}

	private static ArrayList<GraphicObjectHolder> readLineToArrayList(String line, int lineNumber, Level lvl) {
		int x = 0;
		ObjectHolderFactory gf = new ObjectHolderFactory();
		ArrayList<GraphicObjectHolder> holder = new ArrayList<GraphicObjectHolder>();
		for (Character c : line.toCharArray()) {
			holder.add(gf.createGraphicObject(c, x, lineNumber, lvl));
			x++;
		}
		return holder;
	}
	/*
	public static ArrayList<SokoAnd> generateGoalLevel(SokoAnd and){
		SokoAnd sokoand = new SokoAnd();
		Predicate check;
		ArrayList<On> targets = new ArrayList<>();
		ArrayList<On> boxes = new ArrayList<>();
		for(Integer key : and.getPrList().keySet()){
			check = and.getPrList().get(key);
			if(check instanceof On && ( ((On)check).getDisp() == 'o' || ((On)check).getDisp() == 'a') ){
				targets.add(((On)check));
			}else if(check instanceof On && ( ((On)check).getDisp() == '@' || ((On)check).getDisp() == '$')){
				
			}
		}
		return null;
	}
	*/
	
	
	public static ArrayList<SokoAnd> generateGoalLevel(Level lvl){
		ArrayList<SokoAnd> combinations = new ArrayList<SokoAnd>();
		
		ArrayList<GraphicObjectHolder> t = new ArrayList<>();
		ArrayList<GraphicObject> b = new ArrayList<>();
		SokoAnd and = new SokoAnd();
		
		for(ArrayList<GraphicObjectHolder> arrLine:lvl.getArr()){
			for(GraphicObjectHolder h:arrLine){
				if (h.getObj() instanceof Box && h instanceof Destination)
					and.addPredicate(new On(box, h.getObj().hashCode()+"", h.getPos(), '$'));
				else if(h.getObj() instanceof Box)
					b.add(h.getObj());
				else if(h instanceof Destination)
					t.add(h);
			}//End of for loop.
		}
		
		Collections.shuffle(t);
		Collections.shuffle(b);
		
		for(int i=0;i<b.size();i++){
			and.addPredicate(new On(box, b.get(i).hashCode()+"", t.get(i).getPos(), '$'));
			and.addPredicate(new On(clear, "", b.get(i).getPos()+"", ' '));
		}
		combinations.add(and);
		return combinations;
	}
	
	
	
	
	public static void GeneratePermutations(ArrayList<ArrayList<Character>> lists, List<String> result, int depth, String current)
	{
	    if(depth == lists.size())
	    {

	       result.add(current);
	       return;
	     }

	    for(int i = 0; i < lists.get(depth).size(); ++i)
	    {
	    	if(depth==i)
	    		depth++;
	        GeneratePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
	    }
	}
}