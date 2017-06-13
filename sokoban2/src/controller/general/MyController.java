package controller.general;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import controller.general.commands.Command;

public abstract class MyController implements Controller {
	
	private BlockingQueue<Command> queue;
	private boolean stop;
	
	public MyController() {
		queue =  new ArrayBlockingQueue<Command>(1024);
		stop = false;
	}
	
	@Override
	public void insertCommand(Command command) {
		try 
		{
			queue.put(command);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}	
	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (! stop)
				{
					try 
					{
						queue.take().execute();
					} 
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}	
			}
		}).start();	
	}

	@Override
	public void stop() {
		stop = true;
	}	
}