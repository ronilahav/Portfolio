package controller.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import controller.general.Controller;
import controller.sokoban.commands.ExitCommand;

public class MyServer {
	
	private int port;
	private ClientHandler ch;
	private Controller c;
	private volatile boolean stop;
	private boolean isOn;
	
	public MyServer(int port,ClientHandler ch, Controller c)
	{
		this.port = port;
		this.ch = ch;
		this.c = c;
		stop = false;
		isOn = false;
	}
	
	public boolean isOn() {
		return isOn;
	}

	private void runServer()
	{
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			server.setSoTimeout(1000);
			while(!stop)
			{
				try 
				{
					Socket aClient=server.accept();
					
					new Thread(new Runnable()
					{ 
						public void run() 
						{
								try
								{
									ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
									aClient.getInputStream().close();
									aClient.getOutputStream().close();
									aClient.close();
								} 
								catch (IOException e) {
									e.printStackTrace();
								}
						}
					}).start();
				} 
				catch (SocketTimeoutException e) 
				{
					//e.printStackTrace();
				}
	
			}
			server.close(); 
		} 
		catch (BindException e2) 
		{
			System.out.println("Port already in use, bye bye...");
			new ExitCommand(c, ch).execute();
			return;
			//e2.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		} 
		
	}
	
	public void start()
	{
		new Thread(new Runnable() 
		{
			public void run() 
			{
				try
				{
					stop = false;
					isOn = true;
					runServer();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}	
			}
		}).start();
	}
	
	public void stop()
	{
		stop=true;
		isOn = false;
	}
}