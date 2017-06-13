package sokobanAdapter.predicate;

import predicate.PredicatObject;

public class On extends PredicatObject {
	
	char disp;

	public On(String type,String ID, String value){
		super.id=ID;
		super.type = type;
		super.value = value;
	}
	public On(String type,String ID, String value,char disp){
		super.id=ID;
		super.type = type;
		super.value = value;
		this.disp = disp;
	}
	
	public char getDisp() {
		return disp;
	}
	public void setDisp(char disp) {
		this.disp = disp;
	}
}