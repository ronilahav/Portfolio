package searcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

import action.Action;
import searchable.Searchable;
import state.State;


public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	ArrayList<State<T>> trace;
	
	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes=0;
	}
	
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	protected ArrayList<Action> backTrace(Searchable<T> s, State<T> goalState, State<T> initialState) {
		
		ArrayList<Action> solution = new ArrayList<Action>();
		trace = new ArrayList<State<T>>();
		State<T> source = initialState;
		State<T> destination = goalState;
		State<T> state = goalState;
		
		while (state.getCameFrom() != null)
		{
			trace.add(state);
			state = state.getCameFrom();
		}
		trace.add(state);
		Collections.reverse(trace);
		
		Iterator<State<T>> it = trace.iterator();
		if (it.hasNext())
			source = it.next();
		if (it.hasNext())
			destination = it.next();
		solution.add(s.getAction(source, destination));
		while (it.hasNext())
		{
			source = destination;
			destination = it.next();
			solution.add(s.getAction(source, destination));
		}
		return solution;
	}
}