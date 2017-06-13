package planner;

import java.util.List;

import action.Action;
import plannable.Plannable;


public interface Planner {
	
	List<Action> plan(Plannable plannable);
}