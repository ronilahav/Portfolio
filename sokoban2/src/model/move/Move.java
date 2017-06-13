package model.move;

import model.data.Level;
import model.data.elements.Element;

public interface Move {
	
	public void move (Level l, Element e, String movement);
}