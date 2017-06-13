package predicate;


public interface Predicate {
	
	public boolean isSatisfied(Predicate p);
	public String getValue();
	public void setValue(String value);
	public String getID();
	public String getType();
}