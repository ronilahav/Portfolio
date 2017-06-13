package model.data.levelSavers;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

import model.data.Level;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void saveLevel(Level l, OutputStream out) {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(out));
		e.writeObject(l);
		e.close();
	}
}