package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.levelDisplayers.Level2DDisplayer;

public class MyView extends Observable implements View ,Initializable {
	
	private boolean isLevelNull = true;
	private boolean isLevelComplete = false;
	private Timer t;
	private int count;
	private StringProperty Counter;
	private StringProperty Steps;
	private StringProperty Output;
	private TimerTask tt;
	private char[][] levelData;
	
	@FXML
	Level2DDisplayer levelDisplayer;
	@FXML
	Text timeText;
	@FXML
	Text stepsText;
	@FXML
	Text outputText;
	
	String musicFile = "./resources/STAIRW~1.MP3";
	Media song = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mp = new MediaPlayer(song);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		levelData = null;
		levelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->levelDisplayer.requestFocus());
		Counter = new SimpleStringProperty();
		Steps = new SimpleStringProperty();
		Output = new SimpleStringProperty();
		timeText.textProperty().bind(Counter);
		stepsText.textProperty().bind(Steps);
		outputText.textProperty().bind(Output);
		setOut("Welcome");
		t = new Timer();
		tt = new TimerTask() {	
			@Override
			public void run() {
				Counter.set(""+(count++));
			}
		};
	}
	
	public MediaPlayer getMp() {
		return mp;
	}
	public void setMp(MediaPlayer mp) {
		this.mp = mp;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setLevelData(char[][] levelData) {
		this.levelData = levelData;
		levelDisplayer.setLevelData(levelData, "none");
		if (isLevelComplete)
			setOut("Level Completed!!!");
	}
	public void isLevelComplete(boolean isComplete) {
		isLevelComplete = isComplete;
	}
	public void isCurrentLevelNull(boolean isNull) {
		 isLevelNull = isNull;
	}
	public void startTimer () {
		t.scheduleAtFixedRate(tt, 0, 1000);
	}
	public void stopTimer() {
		t.cancel();
	}
	public void setSteps(int steps) {
		Steps.set(""+steps);
	}
	public void setOut(String out) {
		Output.set(""+out);
	}

	public void loadFile() {
		isLevelComplete = false;
		FileChooser fc = new FileChooser();
		fc.setTitle("open sokoban level file");
		//fc.setInitialDirectory(new File("./resources"));
		fc.setInitialDirectory(new File("./"));

		fc.getExtensionFilters().addAll(new ExtensionFilter("Level Files", "*.txt", "*.xml", "*.obj"));
		File choosen = fc.showOpenDialog(null);
		if (choosen != null)
		{
			setOut("Loading "+choosen.getName());
			setLoad(choosen);
			levelDisplayer.requestFocus();
		}
	}
	public void saveFile() {
		if (! isLevelNull)
		{
			setChanged();
			FileChooser fc = new FileChooser();
			fc.setTitle("save sokoban level file");
			//fc.setInitialDirectory(new File("./resources"));
			fc.setInitialDirectory(new File("./"));
			fc.getExtensionFilters().addAll(new ExtensionFilter("Level Files", "*.txt", "*.xml", "*.obj"));
			File choosen = fc.showSaveDialog(null);
			if (choosen != null)
			{
				setOut("Saving "+choosen.getName());
				setSave(choosen);
			}
		}
		else
			setOut("no level was loaded");
	}
	public void exit() {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Exit");
		notifyObservers(arr);								
	}
	public void move(String s) {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Move");
		arr.add(s);
		notifyObservers(arr);
	}
	public void stopServer() {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("stop server");
		notifyObservers(arr);
	}
	public void startServer() {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("start server");
		notifyObservers(arr);
	}
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.getKeyCode(levelDisplayer.getUpKey()))
		{
			levelDisplayer.setLevelData(levelData, "U");
			move("up");
		}
		else if (event.getCode() == KeyCode.getKeyCode(levelDisplayer.getDownKey()))
		{
			levelDisplayer.setLevelData(levelData, "D");
			move("down");
		}
		else if (event.getCode() == KeyCode.getKeyCode(levelDisplayer.getLeftKey()))
		{
			levelDisplayer.setLevelData(levelData, "L");
			move("left");
		}
		else if (event.getCode() == KeyCode.getKeyCode(levelDisplayer.getRightKey()))
		{
			levelDisplayer.setLevelData(levelData, "R");
			move("right");
		}
	}
	
	private void setLoad(File choosen) {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Load");
		String fileType = choosen.getName().split("\\.")[1];
		arr.add(fileType);
		String fileName = choosen.getAbsolutePath();
		arr.add(fileName);
		notifyObservers(arr);
	}	
	private void setSave(File choosen) {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Save");
		String fileType = choosen.getName().split("\\.")[1];
		arr.add(fileType);
		String fileName = choosen.getAbsolutePath();
		arr.add(fileName);
		notifyObservers(arr);
	}
}