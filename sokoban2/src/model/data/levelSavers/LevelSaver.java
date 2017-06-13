package model.data.levelSavers;

import java.io.OutputStream;

import model.data.Level;

public interface LevelSaver {
	
	public void saveLevel(Level l, OutputStream out);
}