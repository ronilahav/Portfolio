package controller.sokoban.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class LoadFileCommand extends AbstractCommand implements Command {

	//constructors
	public LoadFileCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	//methods
	@Override
	public void execute() {
		
		boolean found = false;
		ArrayList<String> arrS = null;
		int i;
		for (i=0; !found && i<commandsData.size(); i++)
			if (commandsData.get(i).get(0).equals("Load"))
			{
				found = true;
				arrS = commandsData.get(i);
			}
		commandsData.remove(arrS);
		
		String fileType = arrS.get(1);
		String fileName = arrS.get(2);
		
		FileInputStream in;
		try 
		{
			in = new FileInputStream(fileName);
			model.loadLevel(fileType, in);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}