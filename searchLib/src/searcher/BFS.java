package searcher;

import java.util.ArrayList;
import java.util.HashSet;

import action.Action;
import searchable.Searchable;
import state.State;

public class BFS<T> extends CommonSearcher<T> {
	
	@Override
	public ArrayList<Action> search(Searchable<T> s) {
		
		openList.add(s.getInitialState());
		ArrayList<State<T>> successors;
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		State<T> n;
		
		while (openList.size() > 0)
		{
			n = popOpenList();		// dequeue
			closedSet.add(n);
			
			if (n.equals(s.getGoalState()))
				return backTrace(s, s.getGoalState(), s.getInitialState());
			
			successors = s.getAllPossibleStates(n);		
			for (State<T> state: successors)
			{
				if(!closedSet.contains(state) && !openList.contains(state))
				{
					state.setCameFrom(n);
					openList.add(state);
				}
				else if (state.isBetterPath())
				{
					if (!openList.contains(state))
					{
						closedSet.remove(state);
						openList.add(state);
					}
					else
					{
						openList.remove(state);
						openList.add(state);
					}	
				}
			}
		}
		return null;
	}
}