package model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.elements.BoxDestination;
import model.data.elements.Element;
import model.data.position.Position;

@SuppressWarnings("serial")
public class Level implements Serializable {
	
	private ArrayList<Element> arrLevel;
	private HashMap<Position, ArrayList<Element>> posEL;
	private boolean levelComplete;
	private ArrayList<Element> boxDestinationList;
	private int moves;
	private int time;
	
	//constructors
	public Level() {
		arrLevel = new ArrayList<Element>();
		initHashMap();
		initBoxDestinationList();
		levelComplete = false;
		moves = 0;
		time = 0;
	}
	public Level(ArrayList<Element> arrLevel) {
		this.arrLevel = new ArrayList<Element>();
		this.arrLevel.addAll(arrLevel);
		initHashMap();
		initBoxDestinationList();
		levelComplete = false;
		moves = 0;
		time = 0;
	}
	//getters and setters
	public void setArrLevel(ArrayList<Element> arrLevel) {
		this.arrLevel = arrLevel;
		initHashMap();
		initBoxDestinationList();
	}
	public ArrayList<Element> getArrLevel() {
		return arrLevel;
	}
	public HashMap<Position, ArrayList<Element>> getPosEL() {
		return posEL;
	}
	public void setPosEL(HashMap<Position, ArrayList<Element>> posEL) {
		this.posEL = posEL;
	}
	public boolean isLevelComplete() {
		return levelComplete;
	}
	public void setLevelComplete(boolean levelComplete) {
		this.levelComplete = levelComplete;
	}
	public ArrayList<Element> getBoxDestination() {
		return boxDestinationList;
	}
	public void setBoxDestination(ArrayList<Element> boxDestination) {
		this.boxDestinationList = boxDestination;
	}
	public int getMoves() {
		return moves;
	}
	public void setMoves(int moves) {
		this.moves = moves;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	//methods
	public ArrayList<Element> ElementInPosition(Position p) {
		return posEL.get(p);
	}
	
	private void initHashMap() {
		posEL = new HashMap<Position, ArrayList<Element>>();
		if (arrLevel != null)
			for (Element e : arrLevel)
				if (posEL.get(e.getPosition()) == null)
				{
					ArrayList<Element> newPositin = new ArrayList<Element>();
					newPositin.add(e);
					posEL.put(e.getPosition(), newPositin);
				}
				else
					posEL.get(e.getPosition()).add(e);
	}
	
	private void initBoxDestinationList () {
		boxDestinationList = new ArrayList<Element>();
		if (arrLevel != null)
			for (Element e : arrLevel)
				if (e.getClass().equals(new BoxDestination().getClass()))
					boxDestinationList.add(e);
	}
}