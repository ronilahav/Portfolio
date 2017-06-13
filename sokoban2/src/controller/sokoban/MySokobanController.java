package controller.sokoban;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.general.Controller;
import controller.general.MyController;
import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.server.MyServer;
import controller.sokoban.commands.AfterSaveCommand;
import controller.sokoban.commands.DisplayLevelCommand;
import controller.sokoban.commands.ExitCommand;
import controller.sokoban.commands.InitAfterLoadCommand;
import controller.sokoban.commands.InitAfterMoveCommand;
import controller.sokoban.commands.IsCurrentLevelNullCommand;
import controller.sokoban.commands.LoadFileCommand;
import controller.sokoban.commands.MoveCommand;
import controller.sokoban.commands.SaveFileCommand;
import controller.sokoban.commands.StartServerCommand;
import controller.sokoban.commands.StopServerCommand;
import model.Model;
import model.data.Level;
import model.data.elements.Element;
import model.policy.Policy;
import view.View;

public class MySokobanController extends MyController implements Controller, Observer {

	private View v;
	private Model m;
	private ClientHandler ch;
	private MyServer s;
	private HashMap<String, Command> commands;
	private Level currentLevel;
	private Policy policy;
	private ArrayList<ArrayList<String>> commandsData;
	private Element [][][] matrix3D;

	public MySokobanController(View v, Model m, ClientHandler ch) {
		super();
		this.v = v;
		this.m = m;
		this.ch = ch;
		commandsData = new ArrayList<ArrayList<String>>();
		currentLevel = null;
		policy = null;
		matrix3D = null;	
		initHashMaps();
	}
	
	public ArrayList<ArrayList<String>> getCommandsData() {
		return commandsData;
	}
	public Element[][][] getMatrix3D() {
		return matrix3D;
	}
	public void setMatrix3D(Element[][][] matrix3d) {
		matrix3D = matrix3d;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Level getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
	public void setServer(MyServer s) {
		this.s = s;
	}
	public MyServer getServer() {
		return s;
	}
	public void extraExit() {
		v.stopTimer();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == v || o == m || o == ch)
		{
			@SuppressWarnings("unchecked")
			ArrayList<String> arr = (ArrayList<String>)arg;
			String str = arr.get(0);
			commandsData.add(arr);
			insertCommand(commands.get(str));
		}
	}
	private void initHashMaps() {
		commands = new HashMap<String, Command>();
		commands.put("Exit", new ExitCommand(this, v, m, ch));
		commands.put("Load", new LoadFileCommand(this, v, m, ch));
		commands.put("Save", new SaveFileCommand(this, v, m, ch));
		commands.put("Move", new MoveCommand(this, v, m, ch));		
		commands.put("level was loaded", new InitAfterLoadCommand(this, v, m, ch));	
		commands.put("init after load is done", new DisplayLevelCommand(this, v, m, ch));
		commands.put("element moved", new InitAfterMoveCommand(this, v, m, ch));
		commands.put("is level null?", new IsCurrentLevelNullCommand(this, v, m, ch));
		commands.put("level was saved", new AfterSaveCommand(this, v, m, ch));
		commands.put("init after move is done", new DisplayLevelCommand(this, v, m, ch));
		commands.put("stop server", new StopServerCommand(this, v, m, ch));
		commands.put("start server", new StartServerCommand(this, v, m, ch));
	}
}