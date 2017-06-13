package action;

import predicate.And;
import predicate.PredicatObject;

public class Action extends PredicatObject {
	
	private And effects;
	private And precondition;
	private String name;
	
	public Action(And effects, And precondition, String name) {
		super();
		this.effects = effects;
		this.precondition = precondition;
		this.name = name;
	}
	public Action(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public And getEffects() {
		return effects;
	}
	public void setEffects(And effects) {
		this.effects = effects;
	}
	public And getPrecondition() {
		return precondition;
	}
	public void setPrecondition(And precondition) {
		this.precondition = precondition;
	}
}