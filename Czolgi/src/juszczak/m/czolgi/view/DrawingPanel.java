package juszczak.m.czolgi.view;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

import juszczak.m.czolgi.message.Message;
import juszczak.m.czolgi.message.MovementMessage;
import juszczak.m.czolgi.message.StartFireMessage;
import juszczak.m.czolgi.message.StopFireMessage;
import juszczak.m.czolgi.message.StopMovementMessage;
import juszczak.m.czolgi.movement.Direction;


class DrawingPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5397906474502589607L;
	private DrawFrame drawFrame;

	public DrawingPanel(BlockingQueue<Message> msgQueue)
	{
		drawFrame = null;
		initializeKeyEvents(msgQueue);
	}
	
	private void initializeKeyEvents(BlockingQueue<Message> msgQueue)
	{
		this.addKeyListener( new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				Message msg = null;
				
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						msg = new MovementMessage(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						msg = new MovementMessage(Direction.DOWN);
						break;
					case KeyEvent.VK_LEFT:
						msg = new MovementMessage(Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						msg = new MovementMessage(Direction.RIGHT);
						break;
					case KeyEvent.VK_SPACE:
						msg = new StartFireMessage();
						break;
						
				}
				
				if (msg != null)
					msgQueue.offer(msg);
			}

			public void keyReleased(KeyEvent e)
			{
				Message msg = null;
				
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						msg = new StopMovementMessage(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						msg = new StopMovementMessage(Direction.DOWN);
						break;
					case KeyEvent.VK_LEFT:
						msg = new StopMovementMessage(Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						msg = new StopMovementMessage(Direction.RIGHT);
						break;
					case KeyEvent.VK_SPACE:
						msg = new StopFireMessage();
						break;
						
				}
				
				if (msg != null)
					msgQueue.offer(msg);
			}
		});
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawFrame.draw(g);
	}
	
	public void addDrawFrame(DrawFrame drawFrame)
	{
		this.drawFrame = drawFrame;
	}
	
}
