package model.data.elements;

import model.data.position.Position;

@SuppressWarnings("serial")
public class Wall extends Element {
	
	//constructors
	public Wall(Position position) {
		super(position);
	}
	public Wall() {
	}

	//methods
	@Override
	public String toString() {
		return "#";
	}
}