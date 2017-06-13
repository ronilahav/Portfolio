package view.levelDisplayers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Level2DDisplayer extends Canvas implements LevelDisplayer {

	private char[][] LevelData;
	private StringProperty wallFileName;
	private StringProperty floorFileName;
	private StringProperty boxFileName;
	private StringProperty boxDestinationFileName;
	
	private StringProperty characterFileName;
	private StringProperty characterFileNameW;
	private StringProperty characterDW;
	private StringProperty characterUS;
	private StringProperty characterUW;
	private StringProperty characterRS;
	private StringProperty characterRW;
	private StringProperty characterLS;
	private StringProperty characterLW;
	
	private StringProperty upKey;
	private StringProperty downKey;
	private StringProperty leftKey;
	private StringProperty rightKey;
	
	//constructors
	public Level2DDisplayer() {
		wallFileName = new SimpleStringProperty();
		floorFileName = new SimpleStringProperty();
		boxFileName = new SimpleStringProperty();
		boxDestinationFileName = new SimpleStringProperty();
		
		characterFileName = new SimpleStringProperty();	
		characterFileNameW = new SimpleStringProperty();	
		characterDW = new SimpleStringProperty();
		characterUS = new SimpleStringProperty();
		characterUW = new SimpleStringProperty();
		characterRS = new SimpleStringProperty();
		characterRW = new SimpleStringProperty();
		characterLS = new SimpleStringProperty();
		characterLW = new SimpleStringProperty();
		
		upKey = new SimpleStringProperty();
		downKey = new SimpleStringProperty();
		leftKey = new SimpleStringProperty();
		rightKey = new SimpleStringProperty();
	}

	public String getDownKey() {
		return downKey.get();
	}
	public void setDownKey(String downKey) {
		this.downKey.set(downKey);
	}
	public String getLeftKey() {
		return leftKey.get();
	}
	public void setLeftKey(String leftKey) {
		this.leftKey.set(leftKey);
	}
	public String getRightKey() {
		return rightKey.get();
	}
	public void setRightKey(String rightKey) {
		this.rightKey.set(rightKey);
	}
	public String getUpKey() {
		return upKey.get();
	}
	public void setUpKey(String upKey) {
		this.upKey.set(upKey);
	}
	public String getCharacterFileName() {
		return characterFileName.get();
	}
	public void setCharacterFileName(String characterFileName) {
		this.characterFileName.set(characterFileName);
	}
	public String getCharacterFileNameW() {
		return characterFileNameW.get();
	}
	public void setCharacterFileNameW(String characterFileNameW) {
		this.characterFileNameW.set(characterFileNameW);
	}
	public String getCharacterDW() {
		return characterDW.get();
	}
	public void setCharacterDW(String characterDW) {
		this.characterDW.set(characterDW);
	}
	public String getCharacterUS() {
		return characterUS.get();
	}
	public void setCharacterUS(String characterUS) {
		this.characterUS.set(characterUS);
	}
	public String getCharacterUW() {
		return characterUW.get();
	}
	public void setCharacterUW(String characterUW) {
		this.characterUW.set(characterUW);
	}	
	public String getCharacterRS() {
		return characterRS.get();
	}
	public void setCharacterRS(String characterRS) {
		this.characterRS.set(characterRS);
	}
	public String getCharacterRW() {
		return characterRW.get();
	}
	public void setCharacterRW(String characterRW) {
		this.characterRW.set(characterRW);
	}
	public String getCharacterLS() {
		return characterLS.get();
	}
	public void setCharacterLS(String characterLS) {
		this.characterLS.set(characterLS);
	}
	public String getCharacterLW() {
		return characterLW.get();
	}
	public void setCharacterLW(String characterLW) {
		this.characterLW.set(characterLW);
	}	
	public String getBoxDestinationFileName() {
		return boxDestinationFileName.get();
	}
	public void setBoxDestinationFileName(String boxDestinationFileName) {
		this.boxDestinationFileName.set(boxDestinationFileName);
	}
	public String getBoxFileName() {
		return boxFileName.get();
	}
	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);
	}
	public String getFloorFileName() {
		return floorFileName.get();
	}
	public void setFloorFileName(String floorFileName) {
		this.floorFileName.set(floorFileName);
	}
	public String getWallFileName() {
		return wallFileName.get();
	}
	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}
	
	public void setLevelData(char[][] levelData, String direction) {
		if (levelData != null)
		{
			long milsec = 50;
			LevelData = levelData;
			switch (direction) {
			case "none":
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//redraw("characterW");
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("character");
				break;
			case "D":
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("character");
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("DW");
				break;
			case "U":
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("US");
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("UW");
				break;
			case "R":
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("RS");
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("RW");
				break;
			case "L":
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("LS");
				try {
					Thread.sleep(milsec);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redraw("LW");
				break;
			}
		}
		
	}

	public void redraw(String characterDirection) {
		if (LevelData != null)
		{
			double W = getWidth();
			double H = getHeight();
			double w = W / LevelData[0].length;
			double h = H / LevelData.length;
			
			GraphicsContext gc = getGraphicsContext2D();
			
			Image wall = null;
			Image floor = null;
			Image box = null;
			Image boxDestination = null;
			Image character = null;
			try 
			{
				wall = new Image(new FileInputStream(wallFileName.get()));
				//floor = new Image(new FileInputStream(floorFileName.get()));
				box = new Image(new FileInputStream(boxFileName.get()));
				boxDestination = new Image(new FileInputStream(boxDestinationFileName.get()));
				switch (characterDirection) {
				case "character":
					character = new Image(new FileInputStream(characterFileName.get()));
					break;
				case "characterW":
					character = new Image(new FileInputStream(characterFileNameW.get()));
					break;
				case "DW":
					character = new Image(new FileInputStream(characterDW.get()));
					break;
				case "US":
					character = new Image(new FileInputStream(characterUS.get()));					
					break;
				case "UW":
					character = new Image(new FileInputStream(characterUW.get()));
					break;
				case "RS":
					character = new Image(new FileInputStream(characterRS.get()));
					break;
				case "RW":
					character = new Image(new FileInputStream(characterRW.get()));
					break;
				case "LS":
					character = new Image(new FileInputStream(characterLS.get()));
					break;
				case "LW":
					character = new Image(new FileInputStream(characterLW.get()));
					break;
				default:
					character = new Image(new FileInputStream(characterFileName.get()));
					break;
				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			gc.clearRect(0, 0, W, H);
			for (int i=0; i<LevelData.length; i++)
			{
				for (int j=0; j<LevelData[0].length; j++)
					if (LevelData[i][j] == '#')
						if (wall == null)
						{
							gc.setFill(Color.BLACK);
							gc.fillRect(j*w, i*h, w, h);
						}
						else
							gc.drawImage(wall, j*w, i*h, w, h);
					else if (LevelData[i][j] == ' ')
						if (floor == null)
						{
							gc.setFill(Color.WHITESMOKE);
							gc.fillRect(j*w, i*h, w, h);
						}	
						else
							gc.drawImage(floor, j*w, i*h, w, h);
					else if (LevelData[i][j] == '@')
						if (box == null)
						{
							gc.setFill(Color.BROWN);
							gc.fillRect(j*w, i*h, w, h);
						}	
						else
							gc.drawImage(box, j*w, i*h, w, h);
					else if (LevelData[i][j] == 'o')
						if (boxDestination == null)
						{
							gc.setFill(Color.RED);
							gc.fillRect(j*w, i*h, w, h);
						}
						else
							gc.drawImage(boxDestination, j*w, i*h, w, h);
					else if (LevelData[i][j] == 'A')
						if (character == null)
						{
							gc.setFill(Color.PURPLE);
							gc.fillOval(j*w, i*h, w, h);
						}
						else
							gc.drawImage(character, j*w, i*h, w, h);
			}
		}
	}	
}