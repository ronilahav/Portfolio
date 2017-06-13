package model.data.levelLoaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.data.Level;
import model.data.elements.Box;
import model.data.elements.BoxDestination;
import model.data.elements.Character;
import model.data.elements.Element;
import model.data.elements.Wall;
import model.data.position.Position;
import model.data.position.Position2D;

public class MyTextLevelLoader implements LevelLoader {

	private ArrayList<Element> arrLevel;
	/*
	 * implements only Position2D
	 */
	@Override
	public Level loadLevel(InputStream in) {
		
		arrLevel = new ArrayList<Element>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		int i=0;
		int j=0;
		
		ArrayList<Integer> arrPos = new ArrayList<Integer>();
		arrPos.add(j);
		arrPos.add(i);
		
		try 
		{
			while ((line = reader.readLine()) != null)
			{
				for(i=0; i<line.length(); i++)
				{
					Position p = new Position2D(arrPos);
					p.setPositionAtIndex(j, 0);
					p.setPositionAtIndex(i, 1);	
					switch (line.charAt(i)) 
					{
						case '#':
							arrLevel.add(new Wall(p));
							break;
						case '@':
							arrLevel.add(new Box(p));
							break;
						case 'o':
							arrLevel.add(new BoxDestination(p));
							break;
						case 'A':
							arrLevel.add(new Character(p));
							break;
					}
				}
				j++;
			} 
			reader.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Level(arrLevel);
	}
}