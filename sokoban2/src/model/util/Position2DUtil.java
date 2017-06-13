package model.util;

import model.data.position.Position;
import model.data.position.Position2D;

public class Position2DUtil {
	
	public Position getUpPosition (Position p){
		Position up = null;
		up = new Position2D(p.getPositionAtIndex(0)-1 , p.getPositionAtIndex(1));
		return up;
	}
	public Position getDownPosition (Position p){
		Position down = null;
		down = new Position2D(p.getPositionAtIndex(0)+1 , p.getPositionAtIndex(1));
		return down;
	}
	public Position getLeftPosition (Position p){
		Position left = null;
		left = new Position2D(p.getPositionAtIndex(0) , p.getPositionAtIndex(1)-1);
		return left;
	}
	public Position getRightPosition (Position p){
		Position right = null;
		right = new Position2D(p.getPositionAtIndex(0) , p.getPositionAtIndex(1)+1);
		return right;
	}
}