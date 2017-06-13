package model.policy;

import java.util.ArrayList;

import model.data.Level;
import model.data.elements.Box;
import model.data.elements.Element;
import model.data.position.Position;
import model.util.LevelToMatrixUtil;
import model.util.Position2DUtil;

public class MySokobanPolicy implements Policy {

	@Override
	public boolean isMovePossible(Level l, Element e, String movement) {
		boolean isPossible = false;
		Position pOfMove = null;
		Position pe = null;
		Position pIfBoxOfMove = null;
		Element eInP = null;
		Element eIfBox = null;
		if(e.toString() == "A")
		{
			pe = e.getPosition();
			switch (movement) {
			case "up":
				if(e.getPosition().getPositionAtIndex(0) > 0)
				{
					pOfMove = new Position2DUtil().getUpPosition(pe);
					eInP = elementInList(l, pOfMove);
					if (eInP == null)
						isPossible =true;
					else if(checks(eInP, "o"))
						isPossible =true;
					else if (! checks(eInP, "#"))					
					{
						pIfBoxOfMove = new Position2DUtil().getUpPosition(pOfMove);
						eIfBox = elementInList(l, pIfBoxOfMove);
						isPossible = eIfBox==null || checks(eIfBox, "o");
					}	
					
				}
				break;
				
			case "down":
				if(e.getPosition().getPositionAtIndex(0) < new LevelToMatrixUtil(new MySokobanPolicy()).maxPosition(l, 0))
				{
					pOfMove = new Position2DUtil().getDownPosition(pe);
					eInP = elementInList(l, pOfMove);
					if (eInP == null)
						isPossible =true;
					else if(checks(eInP, "o"))
						isPossible =true;
					else if (! checks(eInP, "#"))					
					{
						pIfBoxOfMove = new Position2DUtil().getDownPosition(pOfMove);
						eIfBox = elementInList(l, pIfBoxOfMove);
						isPossible = eIfBox==null || checks(eIfBox, "o");
					}
				}
				break;
				
			case "left":
				if(e.getPosition().getPositionAtIndex(1) > 0)
				{
					pOfMove = new Position2DUtil().getLeftPosition(pe);
					eInP = elementInList(l, pOfMove);
					if (eInP == null)
						isPossible =true;
					else if(checks(eInP, "o"))
						isPossible =true;
					else if (! checks(eInP, "#"))					
					{
						pIfBoxOfMove = new Position2DUtil().getLeftPosition(pOfMove);
						eIfBox = elementInList(l, pIfBoxOfMove);
						isPossible = eIfBox==null || checks(eIfBox, "o");
					}
				}
				break;
				
			case "right":
				if(e.getPosition().getPositionAtIndex(0) < new LevelToMatrixUtil(new MySokobanPolicy()).maxPosition(l, 1))
				{
					pOfMove = new Position2DUtil().getRightPosition(pe);
					eInP = elementInList(l, pOfMove);
					if (eInP == null)
						isPossible =true;
					else if(checks(eInP, "o"))
						isPossible =true;
					else if (! checks(eInP, "#"))					
					{
						pIfBoxOfMove = new Position2DUtil().getRightPosition(pOfMove);
						eIfBox = elementInList(l, pIfBoxOfMove);
						isPossible = eIfBox==null || checks(eIfBox, "o");
					}
				}
				break;	
			}
		}
		return isPossible;
	}

	@Override
	public int maxElementInOnePosition() {
		return 2;
	}

	@Override
	public Element WhichToDisplay(Element[] arr) {
		//in one position there can be 2 elements: (o, @) or (o, A)
		Element element = null;
		for (Element e : arr)
			switch (e.toString()) {
			case "@":
				element = e;
				break;
			case "A":
				element = e;
				break;
			}
		return element;
	}
	
	private boolean checks (Element e, String s)
	{
		boolean boo = false;
		if (e.toString().equals(s))
			boo = true;
		return boo;
	}
	
	public Element elementInList (Level l, Position p)
	{
		Element eInP = null;
		if (l.ElementInPosition(p) == null)
			eInP =null;
		else if(l.ElementInPosition(p).size() == 1)
			eInP = l.ElementInPosition(p).get(0);
		else
			for (Element element : l.ElementInPosition(p))
				if (! element.toString().equals("o"))
					eInP = element;
		return eInP;
	}

	public boolean isLevelValid (Level l) {
		boolean valid = false;
		int len;
		ArrayList<Element> arr = null;
		if (l != null)
		{
			for (Element e : l.getArrLevel())
			{
				arr = l.getPosEL().get(e.getPosition());
				len = arr.size();
				if (len == 1)
					valid = true;
				else if (len == 2 && (arr.get(0).toString().equals("o") || arr.get(1).toString().equals("o")))
					valid = arr.get(0).toString().equals("A") || arr.get(1).toString().equals("A")
							|| arr.get(0).toString().equals("@") || arr.get(1).toString().equals("@");
			}
		}
		return valid;
	}	 

	public boolean isLevelComplete (Level l) {
		boolean complete = false;
		if (l != null)
		{
			complete = true;
			boolean flag = false;
			ArrayList<Element> arr = null;
			for (Element e : l.getBoxDestination())
			{
				flag = false;
				arr = l.getPosEL().get(e.getPosition());
				for (Element e2 : arr)
					if (e2.getClass().equals(new Box().getClass()))
					{
						flag = true;
						break;
					}
				if (!flag)
				{
					complete = false;
					break;
				}
			}		
		}
		return complete;
	}
}