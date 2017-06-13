package model.util;

import model.data.Level;
import model.data.elements.Element;
import model.policy.Policy;

public class LevelToMatrixUtil {
	
private Policy policy;
	
	//constructors
	public LevelToMatrixUtil(Policy policy) {
		super();
		this.policy = policy;
	}

	public Element[][][] levelToMatrix (Level l)
	{
		Element [][][] matrix = new Element[maxPosition(l, 0)+1][maxPosition(l, 1)+1][policy.maxElementInOnePosition()];
		for(Element e : l.getArrLevel())
		{
			int x = e.getPosition().getPositionAtIndex(0);
			int y = e.getPosition().getPositionAtIndex(1);
			boolean flag = true;
			for (int i=0; i < matrix[0][0].length && flag; i++)
				if (matrix[x][y][i] == null)
				{
					matrix[x][y][i] = e;
					flag = false;
				}
		}
		return matrix;
	}
	
	public int maxPosition (Level l, int index)
	{
		int i=-1;
		for (Element e : l.getArrLevel())
		{
			if (e.getPosition().getPositionAtIndex(index) > i)
				i = e.getPosition().getPositionAtIndex(index);
		}
		return i;
	}
}