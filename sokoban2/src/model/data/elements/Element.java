package model.data.elements;

import java.io.Serializable;

import model.data.position.Position;

@SuppressWarnings("serial")
public class Element implements Serializable {
	
	Position position;

	//constructors
	public Element(Position position) {
		super();
		this.position = position;
	}
	public Element() {
		this.position = null;
	}
	
	//getters and setters
	public void setPosition(Position position) {
		this.position = position;
	}
	public Position getPosition() {
		return position;
	}
}