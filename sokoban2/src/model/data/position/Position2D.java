package model.data.position;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Position2D implements Serializable, Position {

private ArrayList<Integer> pos;
	
	//constructors
	public Position2D(ArrayList<Integer> p) {
		pos =  new ArrayList<Integer>();
		if (p != null)
			pos.addAll(p);
	}
	public Position2D(int x, int y){
		pos =  new ArrayList<Integer>();
		pos.add(x);
		pos.add(y);
	}
	public Position2D() {
		pos = new ArrayList<Integer>();
	}
	
	//getters and setters
	public ArrayList<Integer> getPos() {
		return pos;
	}
	public void setPos(ArrayList<Integer> pos) {
		this.pos = pos;
	}
	
	//implementing Position
	public int getDimension(){
		return 2;
	}
	public int getPositionAtIndex(int index) {
		int getPIndex = -1;
		switch (index) {
		case 0:
			if(pos.size() > 0 && pos.get(0) >= 0)
				getPIndex = pos.get(0);
			break;
		case 1:
			if(pos.size() >1 && pos.get(1) >= 0)
				getPIndex = pos.get(1);
			break;
		}
		return getPIndex;
	}
	public void setPositionAtIndex(int value, int index) {
		if(index < getDimension())
		{
			if(pos.size()>index)
			{
				pos.remove(index);
				pos.add(index, value);
			}
			else if(index==0)
					pos.add(index, value);
			else
				{
					pos.add(0, -1);
					pos.add(index, value);
				}
		}
		else
			System.out.println("not valid position");
	}
	
	//methods
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		Position tempP = null;
		if (obj.getClass().toString().equals("class position.Position2D"));
		{
			tempP = (Position2D)obj;
			if(this.getDimension() == tempP.getDimension())
			{
				isEqual = true;
				if((this.getPositionAtIndex(0)>=0 && this.getPositionAtIndex(1)>=0)
						|| (tempP.getPositionAtIndex(0)>=0 && tempP.getPositionAtIndex(1)>=0))
					for(int i=0; i<this.getDimension(); i++)
						if(this.getPositionAtIndex(i) != tempP.getPositionAtIndex(i))
							isEqual = false;
			}
		}
		return isEqual;
	}
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	@Override
	public String toString() {
		if(getPositionAtIndex(0) != -1 && getPositionAtIndex(1) != -1)
			return "x:"+getPositionAtIndex(0)+", y:"+getPositionAtIndex(1);
		return null;
	}
}