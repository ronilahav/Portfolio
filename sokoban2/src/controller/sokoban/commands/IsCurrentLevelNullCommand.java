package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class IsCurrentLevelNullCommand extends AbstractCommand implements Command {
	
	public IsCurrentLevelNullCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
			if (arr.get(0).equals("is level null?"))
			{
				commandsData.remove(arr);
				break;
			}
		
		clientHandler.isCurrentLevelNull(controller.getCurrentLevel()==null);
		if (controller.getCurrentLevel() != null)
			clientHandler.initClient();	
	}
}