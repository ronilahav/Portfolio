package state;

import action.Action;

public class State<T> implements Comparable<State<T>>{
	
	private T state;									// the state represented by T
	private double cost = Double.POSITIVE_INFINITY; 	// cost to reach this state
	private State<T> cameFrom = null;					// the state we came from to this state
	private boolean betterPath = false;
	private Action actionThisCameFrom = null;
	
	public State(T state) {
		this.state=state;
	}	

	public T getState() {
		return state;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public boolean isBetterPath() {
		return betterPath;
	}
	public void setBetterPath(boolean betterPath) {
		this.betterPath = betterPath;
	}
	
	@Override
	public boolean equals(Object obj) {
		return state.equals(((State<T>)obj).state);
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public String toString() {	
		return this.state.toString();
	}

	@Override
	public int compareTo(State<T> o) {
		return (int)(this.getCost()-o.getCost());
	}

	public Action getActionThisCameFrom() {
		return actionThisCameFrom;
	}

	public void setActionThisCameFrom(Action actionThisCameFrom) {
		this.actionThisCameFrom = actionThisCameFrom;
	}	
}