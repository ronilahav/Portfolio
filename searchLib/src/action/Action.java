package action;

public class Action {
	private String action;

	public Action(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return getAction();
	}
}