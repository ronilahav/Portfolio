package predicate;


public class Not extends PredicatObject {
	
	private Predicate pr;
	
	public Not(Predicate pr){
		setPr(pr);
	}
	
	public Predicate getPr() {
		return pr;
	}
	public void setPr(Predicate pr) {
		this.pr = pr;
	}
}