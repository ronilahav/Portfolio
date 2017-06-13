package controller.sokoban.commands;

import java.util.ArrayList;

import controller.general.commands.Command;
import controller.server.ClientHandler;
import controller.sokoban.MySokobanController;
import model.Model;
import model.data.elements.Element;
import model.policy.Policy;
import view.View;

public class DisplayLevelCommand extends AbstractCommand implements Command {

	private Element [][][] matrix3D;
	private Policy policy;
	private char [][] matrix2D;
	
	//constructors
	public DisplayLevelCommand(MySokobanController c, View v, Model m, ClientHandler ch) {
		super(c, v, m, ch);
	}
	
	//methods
	@Override
	public void execute() {
		
		for (ArrayList<String> arr : commandsData)
		{
			if (arr.get(0).equals("init after load is done"))
			{
				commandsData.remove(arr);
				break;
			}
			else if (arr.get(0).equals("init after move is done"))
			{
				commandsData.remove(arr);
				break;
			}
		}
		view.setSteps(model.getMoves());
		policy = controller.getPolicy();
		matrix3D = controller.getMatrix3D();
		matrix2D = matrix3DTo2D(matrix3D);
		view.isLevelComplete(model.isLevelComplete());
		view.setLevelData(matrix2D);
		if (clientHandler.isClientConnected())
		{
			clientHandler.isLevelComplete(model.isLevelComplete());
			clientHandler.setLevelData(matrix2D);
		}
	}
	private char [][] matrix3DTo2D(Element [][][] matrix3D) {
		char [][] matrixToDisplay = new char [matrix3D.length][matrix3D[0].length];
		for (int i=0; i < matrix3D.length; i++)
			for (int j=0; j < matrix3D[0].length; j++)
				if (matrix3D[i][j][0] == null)
					matrixToDisplay[i][j] = ' ';
				else if (policy.maxElementInOnePosition()>1 && matrix3D[i][j][1] != null)
					matrixToDisplay[i][j] = policy.WhichToDisplay(matrix3D[i][j]).toString().charAt(0);
				else
					matrixToDisplay[i][j] = matrix3D[i][j][0].toString().charAt(0);
		return matrixToDisplay;
	}
}