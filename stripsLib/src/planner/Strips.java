package planner;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import action.Action;
import plannable.Plannable;
import predicate.And;
import predicate.Predicate;


public class Strips implements Planner {

	private Plannable plannable;

	public Plannable getPlannable() {
		return plannable;
	}
	public void setPlannable(Plannable plannable) {
		this.plannable = plannable;
	}
	
	@Override
	public List<Action> plan(Plannable plannable) {
		LinkedList<Action> plan=new LinkedList<>();
		Stack<Predicate> stack=new Stack<>();
		this.setPlannable(plannable);
		
		stack.push(plannable.getGoal());
		while(!stack.isEmpty()){
			Predicate top=stack.peek();
			if(! (top instanceof Action)){
				if(!plannable.getKnowledgebase().isSatisfiedBy(top)){ // unsatisfied
					if(top instanceof And){ // multipart
						And c=(And)top; 
						for(Predicate p : plannable.getOrderedListOfPredicates(c)){
							stack.push(p);
						}
					}else{ // single and unsatisfied
						stack.pop();
						Action action=plannable.getsatisfyingAction(top);
						if (action != null)
						{
							stack.push(action);
							stack.push(action.getPrecondition());
						}
						else
						{
							plan = null;
							break;
						}
					}
				}else{
					stack.pop();
				}
			}else{ // top is an action at the top of the stack
				stack.pop();
				Action a=(Action)top;
				plannable.getKnowledgebase().update(a.getEffects());
				plan.add(a);
			}
		}
		return plan;
	}
}