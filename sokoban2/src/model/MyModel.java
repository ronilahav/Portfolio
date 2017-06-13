package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import model.data.Level;
import model.data.elements.Element;
import model.data.levelLoaders.LevelLoader;
import model.data.levelLoaders.MyObjectLevelLoader;
import model.data.levelLoaders.MyTextLevelLoader;
import model.data.levelLoaders.MyXMLLevelLoader;
import model.data.levelSavers.LevelSaver;
import model.data.levelSavers.MyObjectLevelSaver;
import model.data.levelSavers.MyTextLevelSaver;
import model.data.levelSavers.MyXMLLevelSaver;
import model.move.Move2D;
import model.policy.MySokobanPolicy;
import model.policy.Policy;
import model.util.LevelToMatrixUtil;

public class MyModel extends Observable implements Model {

	private Level level;
	private Policy policy;
	private HashMap<String, LevelLoader> loaders;
	private HashMap<String, LevelSaver> savers;
	private Element[][][] matrix3D;
	
	public MyModel() {
		policy = new MySokobanPolicy();
		level = null;
		initHashMaps();
	}	
	
	public Element[][][] getMatrix3D() {
		return matrix3D;
	}
	public Level getCurrentLevel() {
		return level;
	}	
	public Policy getPolicy() {
		return policy;
	}
	public int getMoves() {
		return level.getMoves();
	}
	public int getTime() {
		return level.getTime();
	}
	public void SetTime(int time) {
		level.setTime(time);
	}
	public boolean isLevelComplete() {
		return level.isLevelComplete();
	}
	public boolean isLevelValid() {
		return policy.isLevelValid(level);
	}
	
	public void loadLevel (String fileType, FileInputStream in) {
		
		LevelLoader ll = loaders.get(fileType);
		level = ll.loadLevel(in);
		matrix3D = new LevelToMatrixUtil(policy).levelToMatrix(level);
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("level was loaded");
		notifyObservers(arr);		
	}	
	public void saveLevel (String fileType, FileOutputStream out) {
		
		LevelSaver ls = savers.get(fileType);
		ls.saveLevel(level, out);
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("level was saved");
		notifyObservers(arr);
	}	
	
	public void move (Element e, String movement) {
		new Move2D(policy).move(level, e, movement);
		matrix3D = new LevelToMatrixUtil(policy).levelToMatrix(level);
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("element moved");
		notifyObservers(arr);
	}	
	
	public void sendUpdate (String s) {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(s);
		notifyObservers(arr);
	}
	
	private void initHashMaps() {
		//init loaders
		loaders = new HashMap<String, LevelLoader>();
		loaders.put("txt", new MyTextLevelLoader());
		loaders.put("obj", new MyObjectLevelLoader());
		loaders.put("xml", new MyXMLLevelLoader());
		
		//init savers
		savers = new HashMap<String, LevelSaver>();
		savers.put("txt", new MyTextLevelSaver(policy));
		savers.put("obj", new MyObjectLevelSaver());
		savers.put("xml", new MyXMLLevelSaver());
	}
}