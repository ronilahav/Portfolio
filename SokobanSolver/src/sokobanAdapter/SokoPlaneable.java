package sokobanAdapter;

import java.util.ArrayList;
import java.util.Collections;

import Model.Data.Level;
import plannable.Plannable;
import predicate.And;
import predicate.Predicate;
import searcher.BFS;
import searcher.Searcher;
import sokobanAdapter.actions.SokoActionP;
import sokobanAdapter.predicate.On;
import sokobanAdapter.predicate.SokoAnd;


public class SokoPlaneable implements Plannable {
	
	private Level kbLevel;
	private SokoAnd kb;
	private SokoAnd goal;
	
	public SokoPlaneable(Level kbLevel){
		this.kbLevel = kbLevel;
		kb = (SokoAnd) Util.generatePredicateFromLevel(this.kbLevel);
		goal = Util.generateGoalLevel(this.kbLevel).get(0);		//checking just one goal for now
	}
	
	@Override
	public And getGoal() {	
		return goal;
	}
	@Override
	public And getKnowledgebase() {
		return kb;
	}
	
	@Override
	public ArrayList<Predicate> getOrderedListOfPredicates(And c) {
		ArrayList<Predicate> pList = c.getAllPredicate();
		ArrayList<Predicate> result = new ArrayList<>();
		// add Satisfied Predicates
		int size = pList.size();
		int y = 0;
		for(int x = 0 ; x<size;x++){			
			if(kb.isSatisfiedBy(pList.get(y))){
				result.add(pList.get(y));
				pList.remove(y);
			}else
				y++;
		}
		//add Predicates with type box
		size = pList.size();
		y = 0;
		for(int x = 0 ; x<size;x++){
			if(pList.get(y).getType().equals(Util.box)){	
				result.add(pList.get(y));
				pList.remove(y);
			}else
				y++;
		}
		// add all others
		for(Predicate p : pList)		
			result.add(p);
		//reverse order because of the stack
		Collections.reverse(result);		
		return result;
	}

	@Override
	public actionP.Action getsatisfyingAction(Predicate top) {
		On topOn = (On) top;
		int maxTime = 5;
		if (topOn.getType().equals(Util.box))
		{	
			SokoActionP sortestAction = null;
			int sortestActionSize = Integer.MAX_VALUE;
			long start = System.currentTimeMillis();
			long end;
			
			SokoActionP upActionP = searchForPosition(topOn, "up");
			end = System.currentTimeMillis();
			if (upActionP != null && upActionP.getActionsS().size() < sortestActionSize)
			{
				sortestAction = upActionP;
				sortestActionSize = upActionP.getActionsS().size();
				if (((double)(end-start))/1000 > maxTime)
					return sortestAction;
			}
			
			start = System.currentTimeMillis();
			SokoActionP downActionP = searchForPosition(topOn, "down");
			if (downActionP != null && downActionP.getActionsS().size() < sortestActionSize)
			{
				sortestAction = downActionP;
				sortestActionSize = downActionP.getActionsS().size();
				if (((double)(end-start))/1000 > maxTime)
					return sortestAction;
			}
			
			start = System.currentTimeMillis();
			SokoActionP leftActionP = searchForPosition(topOn, "left");
			if (leftActionP != null && leftActionP.getActionsS().size() < sortestActionSize)
			{
				sortestAction = leftActionP;
				sortestActionSize = leftActionP.getActionsS().size();
				if (((double)(end-start))/1000 > maxTime)
					return sortestAction;
			}
			
			SokoActionP rightActionP = searchForPosition(topOn, "right");
			if (rightActionP != null && rightActionP.getActionsS().size() < sortestActionSize)
			{
				sortestAction = rightActionP;
				sortestActionSize = rightActionP.getActionsS().size();
			}
				
			return sortestAction;
		}
		else
			return null;
	}
	
	private SokoActionP searchForPosition(On topOn, String pos){
		kbLevel = Util.generateLevelFromPredicate(kb);
		//copying kb to not change it
		ArrayList<Predicate> arr = kb.getAllPredicate();
		SokoAnd topGoal = new SokoAnd();		//copying kb to not change it
		for (Predicate p : arr)
			topGoal.addPredicate(p);
		
		SokoAnd andForUpdate = new SokoAnd();
		andForUpdate.update(topOn);
		
		String s = topOn.getType()+topOn.getID();
		On predicateInOriginalLocation = (On) kb.getTyapeAndIdList().get(s.hashCode());	
		if (predicateInOriginalLocation.getDisp() == '@')
			andForUpdate.update(new On(Util.clear, "", predicateInOriginalLocation.getValue(),' '));
		else	// if disp is '$'
			andForUpdate.update(new On(Util.target, "", predicateInOriginalLocation.getValue(),'o'));
		
		On player = (On) kb.getTyapeAndIdList().get(Util.player.hashCode());	
		
		if (kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.clear)
				|| kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.target)
				|| kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.player)
				||(kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.box)
						&& (kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getID().equals(topOn.getID()))))
		{
			if (! kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.player))
			{

				if (kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.clear)
						||kb.getPredicateByValue(getPostionAroundTop(topOn, pos)).getType().equals(Util.box))
					andForUpdate.update(new On(Util.player, "", getPostionAroundTop(topOn, pos),'A'));
				else	//if target
					andForUpdate.update(new On(Util.player, "", getPostionAroundTop(topOn, pos),'a'));
				
				if (player.getDisp() == 'A')
					andForUpdate.update(new On(Util.clear, "", player.getValue(),' '));
				else	//'a'
					andForUpdate.update(new On(Util.target, "", player.getValue(),'o'));	
			}	
			topGoal.update(andForUpdate);	
			ArrayList<actionS.Action> actionsS = search(topGoal);
			if (actionsS == null)
				return null;
			SokoActionP actionP = new SokoActionP(topGoal, kb, "Move_"+topOn.getType()+" "+topOn.getID());
			actionP.setActionsS(actionsS);
			return actionP;
		}
		return null;		
	}
	
	private ArrayList<actionS.Action> search(SokoAnd topGoal) {
		Util.generateLevelFromPredicate(topGoal);
		SokoSearchable searchable = new SokoSearchable(Util.generateLevelFromPredicate(topGoal), kbLevel);
		Searcher<char[][]> bfs = new BFS<>();
		return bfs.search(searchable);
	}
	
	private String getPostionAroundTop(Predicate predicate,String Position){
		String[] x = predicate.getValue().split(",");
		int[] xy = { Integer.parseInt(x[0]) ,  Integer.parseInt(x[1])}; 
		String s = null;
		switch (Position) {
		case "up":
			s = xy[0]+","+(xy[1]-1);
			break;
		case "down":
			s = xy[0]+","+(xy[1]+1);
			break;
		case "left":
			s = (xy[0]-1)+","+xy[1];
			break;
		case "right":
			s = (xy[0]+1)+","+xy[1];
			break;
		}
		return s;
	}
}