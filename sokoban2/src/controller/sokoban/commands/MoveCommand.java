package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import model.data.Level;
import model.data.elements.Element;
import view.View;

public class MoveCommand extends AbstractCommand implements Command {

	public MoveCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	//methods
	@Override
	public void execute() {
		
		boolean found = false;
		ArrayList<String> arrS = null;
		int i;
		for (i=0; !found && i<commandsData.size(); i++)
			if (commandsData.get(i).get(0).equals("Move"))
			{
				found = true;
				arrS = commandsData.get(i);
			}
		commandsData.remove(arrS);
		
		Level currentLevel = controller.getCurrentLevel();
		if (currentLevel != null)
		{
			Element character = null;
			for (Element e : currentLevel.getArrLevel())
				if(e.toString() == "A")
					character = e;
			model.move(character, arrS.get(1));
		}
		else
			clientHandler.printToClient("no level was loaded");
	}
}