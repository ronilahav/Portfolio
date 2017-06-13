package model.data.levelLoaders;

import java.io.InputStream;

import model.data.Level;
import model.data.SerializationUtil;

public class MyObjectLevelLoader implements LevelLoader {

private Level level;
	
	@Override
	public Level loadLevel(InputStream in) {
		
		level= (Level) SerializationUtil.deserialize(in);
		return level;
	}
}