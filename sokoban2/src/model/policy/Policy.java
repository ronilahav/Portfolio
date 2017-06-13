package model.policy;

import model.data.Level;
import model.data.elements.Element;
import model.data.position.Position;

public interface Policy {
	
	public boolean isMovePossible (Level l, Element e, String movement);
	public int maxElementInOnePosition();
	public Element WhichToDisplay (Element[] arr);
	public Element elementInList (Level l, Position p);
	public boolean isLevelValid (Level l);
	public boolean isLevelComplete (Level l);
}