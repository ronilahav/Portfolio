package view;

import Model.DB.SokobanDBManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HighScoreController {
	private SokobanDBManager dbm;
	private String lvlName;
	private String steps;
	private String time;
	@FXML
	private TextField playerName;
	
	public void addToHighScore(){
		dbm.addPlayer(playerName.getText());
		dbm.addScore(playerName.getText(), lvlName, Integer.parseInt(steps), Integer.parseInt(time));
		exit();
	}
	
	public void exit(){
		Stage stage = (Stage) playerName.getScene().getWindow();
	    stage.close();
	}

	public String getLvlName() {
		return lvlName;
	}

	public void setLvlName(String lvlName) {
		this.lvlName = lvlName;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	
	public SokobanDBManager getDbm() {
		return dbm;
	}

	public void setDbm(SokobanDBManager dbm) {
		this.dbm = dbm;
	}
}
