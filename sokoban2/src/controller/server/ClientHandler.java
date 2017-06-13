package controller.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	void handleClient(InputStream inFromClient, OutputStream outToClient);
	public boolean isClientConnected();
	public void isCurrentLevelNull(boolean isNull);
	public void isLevelComplete(boolean isComplete);
	public void setLevelData(char[][] levelData);
	public void initClient ();
	public void setStop(boolean stop);
	public void printToClient (String str);
}