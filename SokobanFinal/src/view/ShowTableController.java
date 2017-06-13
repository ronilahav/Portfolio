package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.DB.SokobanDBManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ShowTableController {
	
	@FXML
	private TableView tableView;
	@FXML
	private ComboBox<String> comboLevel;
	@FXML
	private ComboBox<String> comboUsers;
	
    private TableColumn levelNameCol;
    private TableColumn userNameCol;
    private TableColumn levelTimeCol;
    private TableColumn levelStepsCol;
    private SokobanDBManager manager;
    private String lastLevelName;
    private String lastPlayerName;
    private String steps = "steps";
    private String time = "time";
    private int maxResults = 100;

	public void initialize(SokobanDBManager manager, String levelName) {
		SetManager(manager);
		setComboLevel();
		setComboUsers();
		setTableViwe(levelName);
	}
	public void SetManager (SokobanDBManager manager) {
		this.manager = manager;
	}
	public SokobanDBManager getManager() {
		return manager;
	}
	public void byTime() {
		tableView.setItems(manager.getScoreList(lastPlayerName, lastLevelName, time, maxResults));
	}
	public void bySteps() {
		tableView.setItems(manager.getScoreList(lastPlayerName, lastLevelName, steps, maxResults));
	}
	public void setLevel() {
		tableView.getColumns().setAll(userNameCol, levelStepsCol, levelTimeCol);
		lastLevelName = comboLevel.getValue();
		lastPlayerName = "%";
        tableView.setItems(manager.getScoreList(lastPlayerName, lastLevelName, steps, maxResults));
	}
	public void setUser() {
		tableView.getColumns().setAll(levelNameCol, levelStepsCol, levelTimeCol);
		lastLevelName = "%";
		lastPlayerName = comboUsers.getValue();
        tableView.setItems(manager.getScoreList(lastPlayerName, lastLevelName, steps, maxResults));
	}
		
	public void playerClicked () {
		if (lastPlayerName.equals("%"))
		{
			String selectedPlayer = userNameCol.getCellData(tableView.getSelectionModel().getSelectedIndex()).toString();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("playerShowTable.fxml"));
			try 
			{
				BorderPane root = (BorderPane)loader.load();
				ShowPlayerTableController playertableController = (ShowPlayerTableController)loader.getController();
				Stage stage = new Stage();
				stage.setTitle(selectedPlayer+" Scores Table");
		        Scene scene = new Scene(root,450,400);
		        stage.setScene(scene);
				stage.setAlwaysOnTop(true);
				
				playertableController.initialize(selectedPlayer, getManager());
				
				stage.showAndWait();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	private ComboBox setComboLevel () {
		comboLevel.setItems(manager.getLevelNameList());
		return comboLevel;
	}
	private ComboBox setComboUsers () {
		comboUsers.setItems(manager.getUserNameList());
		return comboUsers;	
	}
	private void setTableViwe (String levelName) {
		initializeColumnsAndTableSettings();
		tableView.getColumns().setAll(userNameCol, levelStepsCol, levelTimeCol);
		lastLevelName = levelName;
		lastPlayerName = "%";
        tableView.setItems(manager.getScoreList(lastPlayerName, lastLevelName, steps, maxResults));
	}
	private void initializeColumnsAndTableSettings() {
		levelNameCol = new TableColumn("Level Name");
        levelNameCol.setCellValueFactory(new PropertyValueFactory("lName"));
        userNameCol = new TableColumn("User Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("uName"));
        levelStepsCol = new TableColumn("Number of steps");
        levelStepsCol.setCellValueFactory(new PropertyValueFactory("steps"));
        levelTimeCol = new TableColumn("Time in secands");
        levelTimeCol.setCellValueFactory(new PropertyValueFactory("time"));
        tableView.setPrefWidth(450);
        tableView.setPrefHeight(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}