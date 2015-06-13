package juszczak.m.czolgi.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import juszczak.m.czolgi.gameticker.GameTicker;
import juszczak.m.czolgi.message.Message;
import juszczak.m.czolgi.model.Model;
import juszczak.m.czolgi.view.View;

public class Controller implements ActionListener, Refresh
{

	GameTicker gameTicker;
	
	private View view;
	private Model model;
	private BlockingQueue<Message> msgQueue;

	public Controller(Model model)
	{
		msgQueue = new LinkedBlockingQueue<Message>();
		this.model = model;
		model.setRefresh(this);
		view = new View(msgQueue);
		gameTicker = new GameTicker(30);
		gameTicker.addActionListener(this);
		startGame();
	}
	
	public void processMessages()
	{
		Message msg;
		while((msg = msgQueue.poll()) != null)
		{
			msg.process(model);
		}
	}

	
	public void startGame()
	{
		model.startGame();
		gameTicker.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		processMessages();
		model.calculate();
	}
	
	public static void main(String args[])
	{
		new Controller(new Model());
	}

	@Override
	public void doRefresh()
	{		
		if(model.gameOver())
		{
			gameTicker.stop();
		}
		
		view.setViewInfo(model.getViewInfo());
		view.refresh();

	}

}
