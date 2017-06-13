package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

public class CLIserver extends Observable implements ClientHandler{

	private boolean isLevelNull = true;
	private boolean isClientConnected = false;
	private boolean isLevelComplete = false;
	private boolean stop;
	private char[][] LevelData;
	private BufferedReader inFromClientBuff;
	private PrintWriter outToClientBuff;
	//private BufferedReader userInput;
	//private PrintWriter outToScreen;
	
	public CLIserver() {
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public void isCurrentLevelNull(boolean isNull) {
		 isLevelNull = isNull;
	}
	public boolean isClientConnected() {
		return isClientConnected;
	}
	public void isLevelComplete(boolean isComplete) {
		isLevelComplete = isComplete;
	}
	public void setLevelData(char[][] levelData) {
		LevelData = levelData;
		//display();
	}
	public void initClient () {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("init after move is done");
		notifyObservers(arr);
	}
	public void printToClient (String str) {
		outToClientBuff.println(str);
		outToClientBuff.flush();
	}
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		stop = false;
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("is level null?");
		notifyObservers(arr);
		try
		{
			isClientConnected = true;
			inFromClientBuff = new BufferedReader(new InputStreamReader(inFromClient));	//input stream from client
			outToClientBuff = new PrintWriter(outToClient);									//output stream to client
			//userInput = new BufferedReader(new InputStreamReader(System.in));			//from keyboard
			//outToScreen = new PrintWriter(System.out);										// out to screen
			
			String line;
			String var = null;
			String[] arr1 = null;
			String commandType = null;
			outToClientBuff.println("Enter command :");
			outToClientBuff.flush();
			while(!stop && !(line=inFromClientBuff.readLine()).equals("exit"))
			{
				arr1 = line.split(" ");
				if(arr1.length == 1)
					commandType = arr1[0];
				else if(arr1.length == 2)
				{
					commandType = arr1[0];
					var = arr1 [1];
				}
				else
				{
					outToClientBuff.println("error");
					outToClientBuff.println();
					outToClientBuff.println("Enter command :");
					outToClientBuff.flush();
					continue;
				}
					
				if(commandType.equals("Load"))
					load(var);
				else if(commandType.equals("Display"))
					display();
				else if(commandType.equals("Move"))
					move(var);
				else if(commandType.equals("Save"))
					save(var);
				else
				{
					outToClientBuff.println("error");
					outToClientBuff.flush();
				}
					
				outToClientBuff.println();
				outToClientBuff.println("Enter command :");
				outToClientBuff.flush();
			}
			outToClientBuff.println("bye");
			outToClientBuff.flush();
			exit();
		} 
		catch(IOException e)
		{ 
			e.printStackTrace();
		}	
	}
	
	private void load(String fileName) {
		outToClientBuff.println("Loading "+fileName);
		outToClientBuff.flush();
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Load");
		String fileType = fileName.split("\\.")[1];
		arr.add(fileType);
		arr.add(fileName);
		notifyObservers(arr);
	}
	
	private void save(String fileName) {
		outToClientBuff.println("Saving "+fileName);
		outToClientBuff.flush();
		if (! isLevelNull)
		{
			setChanged();
			ArrayList<String> arr1 = new ArrayList<String>();
			arr1.add("Save");
			String fileType = fileName.split("\\.")[1];
			arr1.add(fileType);
			arr1.add(fileName);
			notifyObservers(arr1);
		}
		else
		{
			outToClientBuff.println("no level was loaded");
			outToClientBuff.flush();
		}
	}
	
	private void move(String direction) {
		setChanged();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Move");
		arr.add(direction);
		notifyObservers(arr);
	}
	
	private void display() {
		for (int i=0; i < LevelData.length; i++)
		{
			for (int j=0; j < LevelData[0].length; j++)
				outToClientBuff.print(LevelData[i][j]);
			outToClientBuff.println();	
		}
		outToClientBuff.flush();
		if (isLevelComplete)
		{
			outToClientBuff.println("Level Completed!!!");
			outToClientBuff.flush();
		}
	}
	private void exit() {
		isClientConnected = false;
	}
}