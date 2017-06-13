package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class InitAfterLoadCommand extends AbstractCommand implements Command {

	public InitAfterLoadCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
			if (arr.get(0).equals("level was loaded"))
			{
				commandsData.remove(arr);
				break;
			}
		
		if (model.isLevelValid())
		{
			view.getMp().stop();
			//view.getMp().play();
			view.setCount(model.getTime());
			if (controller.getCurrentLevel() == null)
				view.startTimer();
			controller.setCurrentLevel(model.getCurrentLevel());
			view.isCurrentLevelNull(controller.getCurrentLevel()==null);
			if (clientHandler.isClientConnected())
				clientHandler.isCurrentLevelNull(controller.getCurrentLevel()==null);
			controller.setPolicy(model.getPolicy());
			controller.setMatrix3D(model.getMatrix3D());
			model.sendUpdate("init after load is done");
		}
		else
		{
			view.setOut("level is not valid");
			if (clientHandler.isClientConnected())
				clientHandler.printToClient("level is not valid");
		}
	}
}