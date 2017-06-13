package model.move;

import java.util.ArrayList;

import model.data.Level;
import model.data.elements.Element;
import model.data.position.Position;
import model.policy.Policy;
import model.util.Position2DUtil;

public class Move2D implements Move {

private Policy policy;
	
	//constructors
	public Move2D(Policy policy) {
		super();
		this.policy = policy;
	}

	@Override
	public void move(Level l, Element e, String movement) {
		if (policy.isMovePossible(l, e, movement))
		{
			Position p = e.getPosition();
			Position p1 = null;
			Position p2 = null;
			Element element = null;
			switch (movement) {
			case "up":
				p1 = new Position2DUtil().getUpPosition(p);
				element = policy.elementInList(l, p1);
				if(element != null && element.toString() == "@")
				{
					p2 = new Position2DUtil().getUpPosition(p1);
					element.setPosition(p2);
					l.getPosEL().get(p1).remove(element);
					addElement(l, p2, element);
				}
				e.setPosition(p1);
				l.getPosEL().get(p).remove(e);
				addElement(l, p1, e);
				break;
				
			case "down":
				p1 = new Position2DUtil().getDownPosition(p);
				element = policy.elementInList(l, p1);
				if(element != null && element.toString() == "@")
				{
					p2 = new Position2DUtil().getDownPosition(p1);
					element.setPosition(p2);
					l.getPosEL().get(p1).remove(element);
					addElement(l, p2, element);
				}
				e.setPosition(p1);
				l.getPosEL().get(p).remove(e);
				addElement(l, p1, e);
				break;
				
			case "left":
				p1 = new Position2DUtil().getLeftPosition(p);
				element = policy.elementInList(l, p1);
				if(element != null && element.toString() == "@")
				{
					p2 = new Position2DUtil().getLeftPosition(p1);
					element.setPosition(p2);
					l.getPosEL().get(p1).remove(element);
					addElement(l, p2, element);
				}
				e.setPosition(p1);
				l.getPosEL().get(p).remove(e);
				addElement(l, p1, e);
				break;
				
			case "right":
				p1 = new Position2DUtil().getRightPosition(p);
				element = policy.elementInList(l, p1);
				if(element != null && element.toString() == "@")
				{
					p2 = new Position2DUtil().getRightPosition(p1);
					element.setPosition(p2);
					l.getPosEL().get(p1).remove(element);
					addElement(l, p2, element);
				}
				e.setPosition(p1);
				l.getPosEL().get(p).remove(e);
				addElement(l, p1, e);
				break;
			}
			l.setMoves(l.getMoves()+1);
			l.setLevelComplete(policy.isLevelComplete(l));
		}
	}
	
	public void addElement(Level l, Position p, Element e) {
		if (l.getPosEL().get(p) == null)
		{
			ArrayList<Element> newPositin = new ArrayList<Element>();
			newPositin.add(e);
			l.getPosEL().put(p, newPositin);
		}
		else
			l.getPosEL().get(p).add(e);
	}
}