package searcher;

import java.util.ArrayList;

import action.Action;
import searchable.Searchable;
import state.State;


public class Dijkstra<T> extends CommonSearcher<T> {
	
	@Override
	public ArrayList<Action> search(Searchable<T> s) {
		
		State<T> u;
		ArrayList<State<T>> successors;

		getAllVertex(s, s.getInitialState());
		for (State<T> state: openList)
		{
			state.setCameFrom(null);
			state.setCost(Double.POSITIVE_INFINITY);
		}
		s.getInitialState().setCost(0);
		while (openList.size() > 0)
		{
			u = popOpenList();						// dequeue
			successors = s.getAllPossibleStates(u);
			for (State<T> state: successors)
				if (state.isBetterPath())
				{
					openList.remove(state);
					openList.add(state);
					state.setCameFrom(u);
				}
					
		}
		return backTrace(s, s.getGoalState(), s.getInitialState());
	}
	
	private void getAllVertex(Searchable<T> s, State<T> n) {
		
		ArrayList<State<T>> successors;
		successors = s.getAllPossibleStates(n);
		for (State<T> state: successors)
		{
			if (openList.contains(state))
			{
				openList.remove(state);
				openList.add(state);
			}
			else
			{	
				openList.add(state);
				getAllVertex(s, state);
			}
		}
	}
}