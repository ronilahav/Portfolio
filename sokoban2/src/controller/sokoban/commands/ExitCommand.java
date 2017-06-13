package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.Controller;
import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import javafx.application.Platform;
import model.Model;
import view.View;

public class ExitCommand extends AbstractCommand implements Command {

	//constructors
	public ExitCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}
	public ExitCommand(Controller c, ClientHandler ch) {
		super((MySokobanController) c, ch);
	}
	
	//methods
	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
			if (arr.get(0).equals("Exit"))
			{
				commandsData.remove(arr);
				break;
			}
		view.getMp().stop();
		controller.extraExit();
		controller.stop();
		if(controller.getServer() != null)
			controller.getServer().stop();
		if (clientHandler.isClientConnected())
			clientHandler.setStop(true);
		Platform.exit();
	}
}