package sokobanAdapter.actions;

import actionS.Action;

public class SokoActionS extends Action {

	int movment;
	
	public SokoActionS(String action, int movment) {
		super(action);
		this.movment = movment;
	}

	public int getMovment() {
		return movment;
	}
	public void setMovment(int movment) {
		this.movment = movment;
	}
}