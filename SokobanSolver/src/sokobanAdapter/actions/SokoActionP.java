package sokobanAdapter.actions;

import java.util.ArrayList;

import actionP.Action;
import predicate.And;


public class SokoActionP extends Action {
	
	private ArrayList<actionS.Action> actionsS;

	public static final String UP="MOVE_U";
	public static final String DOWN="MOVE_D";
	public static final String LEFT="MOVE_L";
	public static final String RIGHT="MOVE_R";
	
	public SokoActionP(And effects, And precondition, String name) {
		super(effects, precondition, name);
		actionsS = new ArrayList<>();
	}

	public ArrayList<actionS.Action> getActionsS() {
		return actionsS;
	}
	public void setActionsS(ArrayList<actionS.Action> actionsS) {
		this.actionsS = actionsS;
	}
}