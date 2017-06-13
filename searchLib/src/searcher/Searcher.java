package searcher;

import java.util.ArrayList;

import action.Action;
import searchable.Searchable;

public interface Searcher<T> {
	
	public ArrayList<Action> search(Searchable<T> s);	// the search method
	public int getNumberOfNodesEvaluated();				// get how many nodes were evaluated by the algorithm
}