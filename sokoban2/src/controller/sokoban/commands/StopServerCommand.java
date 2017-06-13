package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class StopServerCommand extends AbstractCommand implements Command {

	public StopServerCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
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
		if (controller.getServer() !=null)
		{
			controller.getServer().stop();
			view.setOut("server is off");
		}
		else
			view.setOut("server was not defined");
	}
}