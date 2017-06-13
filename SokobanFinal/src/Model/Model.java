package Model;

import java.util.LinkedList;

import Model.DB.SokobanDBManager;

public interface Model {

	public void save(LinkedList<String> filePath);

	public void move(LinkedList<String> direction);

	public void load(LinkedList<String> filePath);
	
	public void display();
	
	public SokobanDBManager getManager();
	
}
