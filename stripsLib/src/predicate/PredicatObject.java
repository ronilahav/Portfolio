package predicate;


public class PredicatObject implements Predicate {
	
	protected String type;
	protected String id;
	protected String value;
	
	@Override
	public String getValue() {
		return value;
	}
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String getID() {
		return this.id;
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean isSatisfied(Predicate p) {
		return p.hashCode() == this.hashCode();
	}
	
	@Override
	public int hashCode() {
		return (value + id + type).hashCode();
	}		
	@Override
	public String toString() {		
		return getType()+"_"+ getID()+ "("+getValue()+")";
	}	
}