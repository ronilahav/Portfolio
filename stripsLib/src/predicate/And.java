package predicate;

import java.util.ArrayList;
import java.util.HashMap;


public class And extends PredicatObject {
	
	private HashMap<Integer, Predicate> prList;
	private HashMap<Integer, Predicate> tyapeAndIdList;
	
	public And() {
		prList = new HashMap<Integer, Predicate>();
		tyapeAndIdList = new HashMap<Integer, Predicate>();
	}
	
	public HashMap<Integer, Predicate> getPrList() {
		return prList;
	}
	public void setPrList(HashMap<Integer, Predicate> prList) {
		this.prList = prList;
	}		
	public HashMap<Integer, Predicate> getTyapeAndIdList() {
		return tyapeAndIdList;
	}
	public void setTyapeAndIdList(HashMap<Integer, Predicate> tyapeAndIdList) {
		this.tyapeAndIdList = tyapeAndIdList;
	}
	
	public ArrayList<Predicate> getAllPredicate() {
		ArrayList<Predicate> list = new ArrayList<Predicate>();
		Predicate p;
		for (Integer key : prList.keySet())
		{
			p = prList.get(key);
			if (p instanceof And)
			{
				And and = (And) p;
				list.addAll(and.getAllPredicate());
			}
			else
				list.add(p);
		}			
		return list;
	}
	
	public boolean contains(Predicate p){
		return prList.containsKey(p.hashCode());
	}
	
	public boolean isSatisfiedBy(Predicate p) {
		if (p instanceof And) {
			for (Predicate pr : ((And) p).getAllPredicate()) {
				if (!isSatisfiedBy(pr))
					return false;
			}
			return true;
		} else
			return prList.containsKey(p.hashCode());
	}
	
	public boolean addPredicate(Predicate p){
		if(tyapeAndIdList.containsKey(p.hashCode()))
			return false;
		String s = p.getType()+p.getID();
		tyapeAndIdList.put(s.hashCode(), p);
		prList.put(p.hashCode(), p);
		return true;
	}
	
	protected boolean isOkToAdd(Predicate p){
		String s = p.getType()+p.getID();
			if(tyapeAndIdList.get(s.hashCode()).getValue() == p.getValue())//means that we have got the same item. exactly.
				return true;// like x1 + x1 = x1 (boolean algebra) so no need to add it again.
			else
				return false;//the hash is the same. but the value is not. so we wont add it.
	}
	
	public boolean removePredicate(Predicate p){
		if(prList.containsKey(p.hashCode())){
			prList.remove(p.hashCode());
			String s = p.getType()+p.getID();
			tyapeAndIdList.remove(s.hashCode());
			return true;//DELETED!
		}
		return false;//COULD NOT FIND!
	}
	
	public boolean update(Predicate p){//Update value by predicate.
		if (prList.containsKey(p.hashCode()))
			return false;
		String s = p.getType()+p.getID();
		if(tyapeAndIdList.containsKey(s.hashCode())){
			Predicate pr = tyapeAndIdList.get(s.hashCode());
			if (pr.getValue() != p.getValue())
			{
				prList.remove(pr.hashCode());
				prList.put(p.hashCode(), p);
				tyapeAndIdList.get(p.hashCode()).setValue(p.getValue());
				return true;//UPDATED!
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("{");
		for (Integer key : prList.keySet()){
			Predicate p = prList.get(key);
			out.append(" "+ p.getType()+"_"+ p.getID()+ "("+p.getValue() +") &");
		}
		out.deleteCharAt(out.length()-1);
		out.append("}");
		return out.toString();
	}
}