package Model.DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="Levels")
public class LevelDB implements DB,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Set<LevelDB> sl = new HashSet<LevelDB>(0);

	@Id
	@Column(name="levelName")
	String name;
	
	@Column(name="levelString")
	String levelString;
	
	
	@Column(name="difficulty")
	int diff;

	@Override
	public String toString() {
	return "Level Name: "+this.name + 
			", Difficulty: "+this.diff;
	}
	public LevelDB(){
		
	}
	public LevelDB(String name,String map, int diff){
		setDiff(diff);
		setName(name);
		setLevelString(map);
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLevelString() {
		return levelString;
	}


	public void setLevelString(String levelString) {
		this.levelString = levelString;
	}


	public int getDiff() {
		return diff;
	}


	public void setDiff(int diff) {
		this.diff = diff;
	}

	
/*
	public void setSl(Set<LevelDB> sl) {
		this.sl = sl;
	}

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ScoreList", catalog = "SokobanDB", joinColumns = {
			@JoinColumn(name = "levelName", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "userName",
					nullable = false, updatable = false) })
	public Set<LevelDB> getSl() {
		return sl;
	}*/
	
}
