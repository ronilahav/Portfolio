package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import model.data.Level;
import model.data.elements.Element;
import model.policy.Policy;

public interface Model {
	
	public void loadLevel (String fileType, FileInputStream in);
	public void saveLevel (String fileType, FileOutputStream out);
	public Level getCurrentLevel();
	public Policy getPolicy();
	public void move (Element e, String movement);
	public Element[][][] getMatrix3D();
	public void sendUpdate (String s);
	public boolean isLevelComplete();
	public boolean isLevelValid();
	public int getMoves();
	public int getTime();
	public void SetTime(int time);
}