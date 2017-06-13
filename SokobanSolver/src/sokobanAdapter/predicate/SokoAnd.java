package sokobanAdapter.predicate;

import java.util.HashMap;

import predicate.And;
import predicate.Predicate;
import sokobanAdapter.Util;

public class SokoAnd extends And {
	
	// Since it's Sokoban there goin to be only one value of each predicat in a
		// state.
		// Cause more than one Box/Player can not exist on one floor.
		HashMap<String, Integer> valueList = new HashMap<String, Integer>();
		
		public HashMap<String, Integer> getValueList() {
			return valueList;
		}
		public void setValueList(HashMap<String, Integer> valueList) {
			this.valueList = valueList;
		}
		
		public String getTypeByValue(String value){
			if(valueList.containsKey(value))
				return getPrList().get(valueList.get(value)).getType();
			return "NONE";
		}	
		public Predicate getPredicateByValue(String value){
			if(valueList.containsKey(value))
				return getPrList().get(valueList.get(value));
			return null;
		}
		
		@Override
		public boolean isSatisfiedBy(Predicate p) {
			if (p instanceof And) {
				for (Predicate pr : ((And) p).getAllPredicate()) {
					if (!isSatisfiedBy(pr))
						return false;
				}
				return true;
			}
			else
			{
				if (!getPrList().containsKey(p.hashCode()) && p.getType().equals(Util.clear))
				{
					Predicate pInClear = getPrList().get(valueList.get(p.getValue()));
					return (pInClear != null) && (pInClear.getType().equals(Util.player));
				}
				return getPrList().containsKey(p.hashCode());
			}		
		}
		
		@Override
		public boolean addPredicate(Predicate p) {
			if (valueList.containsKey(p.getValue()))
				return false;

			String s = p.getType() + p.getID();
			if(p instanceof And){
				for(Predicate p1 : ((And) p).getAllPredicate())
					this.addPredicate(p1);
			}
				
			if (p.getType().equals(Util.box) && getTyapeAndIdList().containsKey(s.hashCode()))
				return false;

			valueList.put(p.getValue(), p.hashCode());
			getTyapeAndIdList().put(s.hashCode(), p);
			getPrList().put(p.hashCode(), p);
			return true;
		}

		@Override
		public boolean update(Predicate p) {
			boolean bool = true;
			if (p instanceof And) {
				for (Predicate pr : ((And) p).getAllPredicate()) {
					bool = innerUpdate(pr) && bool;
				}
				//TODO
				/****	nees to fix - not O(1) anymore!!!	***/
				for (Integer key : getPrList().keySet())
				{
					Predicate p1 = getPrList().get(key);
					String s = p1.getType() + p1.getID();
					getTyapeAndIdList().put(s.hashCode(), p1);
				}
				return bool;
			} else
				return innerUpdate(p);

		}

		private boolean innerUpdate(Predicate p) {
			if (getPrList().containsKey(p.hashCode()))
				return false;

			String s = p.getType() + p.getID();
			if (p.getType().equals(Util.box)) {
				if (getTyapeAndIdList().containsKey(s.hashCode())) {
					if (valueList.containsKey(p.getValue())) {
						int hash = valueList.remove(p.getValue());
						Predicate pr = getPrList().get(hash);
						getPrList().remove(pr.hashCode());
						String spr = pr.getType() + pr.getID();
						getTyapeAndIdList().remove(spr.hashCode());
					}
					Predicate pr = getTyapeAndIdList().remove(s.hashCode());
					getPrList().remove(pr.hashCode());
					valueList.remove(pr.getValue());

					valueList.put(p.getValue(), p.hashCode());
					getPrList().put(p.hashCode(), p);
					getTyapeAndIdList().put(s.hashCode(), p);
					return true;
				}
			}
			if (!valueList.containsKey(p.getValue())) {
				addPredicate(p);
				return true;
			}
			int hash = valueList.remove(p.getValue());
			Predicate pr = getPrList().get(hash);
			getPrList().remove(pr.hashCode());
			String spr = pr.getType() + pr.getID();
			getTyapeAndIdList().remove(spr.hashCode());

			valueList.put(p.getValue(), p.hashCode());
			getPrList().put(p.hashCode(), p);
			getTyapeAndIdList().put(s.hashCode(), p);
			return true;
		}
}