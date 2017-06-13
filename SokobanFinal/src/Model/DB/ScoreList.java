package Model.DB;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="ScoreList")
public class ScoreList implements DB{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int scoreID;
	
	public ScoreList(){}
	public ScoreList(String userName,String levelName,int steps,int time) {
		setUName(userName);
		setLName(levelName);
		setTime(time);
		setSteps(steps);
	}
	
	@Column(name="userName")
	String uName;

	@Column(name="levelName")
	String lName;
	
	@Column(name="levelSteps")
	int steps;
	
	@Column(name="levelTime")
	int time;

	public String getUName() {
		return uName;
	}

	public void setUName(String uName) {
		this.uName = uName;
	}

	public String getLName() {
		return lName;
	}

	
	public void setLName(String lName) {
		this.lName = lName;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(obj instanceof PlayerDB){
			if(this.uName.equals( ((PlayerDB)obj).getName() ))
					return true;
		}
		else if(obj instanceof LevelDB)
			if(this.uName.equals( ((LevelDB)obj).getName() ))
					return true;
		
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		return Objects.hash(scoreID);
	}
	/*
	@Override
	public String toString() {
		return "";
	}
	*/


}
