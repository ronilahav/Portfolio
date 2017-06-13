package controller.sokoban.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public class SaveFileCommand extends AbstractCommand implements Command {

	public SaveFileCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}

	//methods
	@Override
	public void execute() {
		
		boolean found = false;
		ArrayList<String> arrS = null;
		int i;
		for (i=0; !found && i<commandsData.size(); i++)
			if (commandsData.get(i).get(0).equals("Save"))
			{
				found = true;
				arrS = commandsData.get(i);
			}
		commandsData.remove(arrS);
		
		model.SetTime(view.getCount());
		String fileType = arrS.get(1);
		String fileName = arrS.get(2);
		FileOutputStream out;
		try 
		{
			out = new FileOutputStream(fileName);
			model.saveLevel(fileType, out);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}