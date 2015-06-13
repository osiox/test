package juszczak.m.czolgi.gameticker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTicker implements Runnable
{
	ActionListener listener;
	int delay;
	Thread thread;
	Boolean isTicking;
	
	public GameTicker(int i)
	{
		// TODO Auto-generated constructor stub
		delay = i;
		thread = new Thread(this);
		thread.start();
		stop();
	}
	
	public void addActionListener(ActionListener listener)
	{
		this.listener = listener;
	}
	public void removeActionListener(ActionListener listener)
	{
		this.listener = listener;
	}
	public void fireActionPerformed()
	{
			ActionEvent e = new ActionEvent(this, 0, null);
			listener.actionPerformed(e);
	}
	public void stop()
	{
		isTicking = false;
	}
	
	public void start()
	{
		isTicking = true;
	}
	@Override
	public void run()
	{
		while(true)
		{
			if(listener != null && isTicking)
				fireActionPerformed();
			try
			{
				Thread.sleep(delay);
			}
			catch(InterruptedException interruptedexception)
			{
				System.out.println("WARNING: Ticker thread interrupted.");
			}
		}
		
	}

}
