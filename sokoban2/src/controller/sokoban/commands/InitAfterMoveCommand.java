package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class InitAfterMoveCommand extends AbstractCommand implements Command {

	public InitAfterMoveCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
			if (arr.get(0).equals("element moved"))
			{
				commandsData.remove(arr);
				break;
			}
		
		controller.setCurrentLevel(model.getCurrentLevel());
		controller.setMatrix3D(model.getMatrix3D());
		model.sendUpdate("init after move is done");
	}
}