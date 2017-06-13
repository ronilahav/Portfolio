package model.data.levelSavers;

import java.io.OutputStream;

import model.data.Level;
import model.data.SerializationUtil;

public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level l, OutputStream out) {
		SerializationUtil.serialize(l, out);	
	}
}