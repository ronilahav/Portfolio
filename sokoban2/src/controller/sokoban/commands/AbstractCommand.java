package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import view.View;

public abstract class AbstractCommand implements Command {

	protected View view;
	protected MySokobanController controller;
	protected Model model;
	protected ClientHandler clientHandler;
	protected ArrayList<ArrayList<String>> commandsData;
	
	public AbstractCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super();
		this.controller = c;
		this.view = v;
		this.model = m;
		this.clientHandler = ch;
		commandsData = c.getCommandsData();
	}
	
	public AbstractCommand(MySokobanController controller, ClientHandler clientHandler) {
		super();
		this.controller = controller;
		this.clientHandler = clientHandler;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}
}