package searchable;

import java.util.ArrayList;

import action.Action;
import state.State;

public interface Searchable<T> {

	public State<T> getInitialState();
	public State<T> getGoalState();
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	public Action getAction(State<T> source, State<T> destination);
}