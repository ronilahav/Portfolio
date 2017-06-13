package controller.general;

import controller.general.commands.Command;

public interface Controller {
	
	public void start();
	public void stop();
	public void insertCommand(Command command);
}