package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class AfterSaveCommand extends AbstractCommand implements Command {

	//constructors
	public AfterSaveCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
		}

	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
			if (arr.get(0).equals("level was saved"))
			{
				commandsData.remove(arr);
				break;
			}
		view.setOut("level was saved");
		if (clientHandler.isClientConnected())
			clientHandler.printToClient("level was saved");
	}
}