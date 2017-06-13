package view;

import Model.DB.SokobanDBManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowPlayerTableController {
	
	@FXML
	private TableView tableView;
	private TableColumn levelNameCol;
    private TableColumn levelTimeCol;
    private TableColumn levelStepsCol;
    private String levelName;
    private String playerName;
    private String steps = "steps";
    private String time = "time";
    private String level = "lName";
    private SokobanDBManager manager;
    private int maxResults = 100;

	public void initialize(String player, SokobanDBManager manager) {
		SetManager(manager);
		initializeColumnsAndTableSettings();
		tableView.getColumns().setAll(levelNameCol, levelStepsCol, levelTimeCol);
		levelName = "%";
		playerName = player;
        tableView.setItems(manager.getScoreList(playerName, levelName, steps, maxResults));
	}
	
	public void SetManager (SokobanDBManager manager) {
		this.manager = manager;
	}
	public SokobanDBManager getManager() {
		return manager;
	}
	
	public void byTime() {
		 tableView.setItems(manager.getScoreList(playerName, levelName, time, maxResults));
	}
	public void bySteps() {
		 tableView.setItems(manager.getScoreList(playerName, levelName, steps, maxResults));
	}
	public void byLevel() {
		 tableView.setItems(manager.getScoreList(playerName, levelName, level, maxResults));
	}
	
	private void initializeColumnsAndTableSettings() {
		levelNameCol = new TableColumn("Level Name");
        levelNameCol.setCellValueFactory(new PropertyValueFactory("lName"));
        levelStepsCol = new TableColumn("Number of steps");
        levelStepsCol.setCellValueFactory(new PropertyValueFactory("steps"));
        levelTimeCol = new TableColumn("Time in secands");
        levelTimeCol.setCellValueFactory(new PropertyValueFactory("time"));
        tableView.setPrefWidth(450);
        tableView.setPrefHeight(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}