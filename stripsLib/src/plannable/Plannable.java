package plannable;

import java.util.ArrayList;

import action.Action;
import predicate.And;
import predicate.Predicate;


public interface Plannable {
	
	And getGoal();
	And getKnowledgebase();
	Action getsatisfyingAction(Predicate top);
	ArrayList<Predicate> getOrderedListOfPredicates(And c);
}