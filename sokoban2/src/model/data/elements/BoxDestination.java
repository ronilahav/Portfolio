package model.data.elements;

import model.data.position.Position;

@SuppressWarnings("serial")
public class BoxDestination extends Element {
	
	//constructors
	public BoxDestination(Position position) {
		super(position);
	}
	public BoxDestination() {
	}

	//methods
	@Override
	public String toString() {
		return "o";
	}
}