package model.data.elements;

import model.data.position.Position;

@SuppressWarnings("serial")
public class Box extends Element{
	
	//constructors
	public Box(Position position) {
		super(position);
	}
	public Box() {
	}

	//methods
	@Override
	public String toString() {
		return "@";
	}
}