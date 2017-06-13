package model.data.elements;

import model.data.position.Position;

@SuppressWarnings("serial")
public class Character extends Element {
	
	//constructors
	public Character(Position position) {
		super(position);
	}
	public Character() {
	}

	//methods
	@Override
	public String toString() {
		return "A";
	}
}