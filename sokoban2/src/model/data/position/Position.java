package model.data.position;

import java.io.Serializable;

public interface Position extends Serializable {
	
	public int getDimension();
	public int getPositionAtIndex(int index);
	public void setPositionAtIndex(int value, int index);
}