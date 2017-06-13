package model.data.levelSavers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import model.data.Level;
import model.data.elements.Element;
import model.policy.Policy;
import model.util.LevelToMatrixUtil;

public class MyTextLevelSaver implements LevelSaver {

private Policy policy;
	
	//constructors
	public MyTextLevelSaver(Policy policy) {
		super();
		this.policy = policy;
	}

	/*
	 * implements only Position2D
	 */
	@Override
	public void saveLevel(Level l, OutputStream out) {
		Element [][][] matrix = new LevelToMatrixUtil(policy).levelToMatrix(l);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		String line = "";
		
		for (int i=0; i < matrix.length; i++)
		{
			for (int j=0; j < matrix[0].length; j++)
			{
				if (matrix[i][j][0] != null)
					line += (matrix[i][j][0]).toString();
				else
					line += " ";	
			}			
			try
			{
				writer.write(line);
				writer.newLine();
				line = "";
				
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try 
		{
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}