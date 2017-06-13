package boot;
	
import controller.server.CLIserver;
import controller.server.MyServer;
import controller.sokoban.MySokobanController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.MyModel;
import view.MyView;

public class Run extends Application {
	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GUI.fxml"));
			BorderPane root = (BorderPane)loader.load();
			MyView view = loader.getController();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			MyModel model = new MyModel();
			CLIserver cli = new CLIserver();
			MySokobanController controller = new MySokobanController(view, model, cli);
			
			view.addObserver(controller);
			model.addObserver(controller);
			cli.addObserver(controller);
			controller.start();
			
			if (getParameters().getRaw().size() ==2 && getParameters().getRaw().get(0).equals("-server"))
			{
				int port = Integer.parseInt(getParameters().getRaw().get(1));
				MyServer server=new MyServer(port, cli, controller);
				server.start();
				controller.setServer(server);
				
			}	
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {	
				@Override
				public void handle(WindowEvent event) {
					view.exit();
				}
			});
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}