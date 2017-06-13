package model.data.levelLoaders;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;

import model.data.Level;

public class MyXMLLevelLoader implements LevelLoader {

private Level level;
	
	@Override
	public Level loadLevel(InputStream in) {
		XMLDecoder xd = new XMLDecoder(new BufferedInputStream(in));
		level = (Level) xd.readObject();
		xd.close();
		return level;
	}
}