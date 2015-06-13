package juszczak.m.czolgi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

import juszczak.m.czolgi.message.Message;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;
 
class Okno extends JFrame  implements DrawFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6648399071131431175L;
	final private DrawingPanel panel;
	private LinkedList<ObjectToPaint> viewInfo;
	
	public Okno(BlockingQueue<Message> msgQueue)
	{
		super("Battle Tank");
		viewInfo = null;
		
		getContentPane().setLayout(null);
		
		panel = new DrawingPanel(msgQueue);
		panel.addDrawFrame(this);
		panel.setLayout(null);
		panel.setBounds(0,  0, (int)(ObjectToPaint.getDefaultSize()*25), (int)(ObjectToPaint.getDefaultSize()*25));
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
		setBackground(Color.GRAY);
		panel.setFocusable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setPreferredSize(new Dimension((int)(ObjectToPaint.getDefaultSize()*25),(int)(ObjectToPaint.getDefaultSize()*25)));
		pack();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
    	setVisible(true);
    	setResizable( true );
	}

	@Override
	public void draw(Graphics g)
	{
		if(viewInfo != null)
			for(ObjectToPaint pF: viewInfo)
				pF.draw(g);
	}

	public void setViewInfo(LinkedList<ObjectToPaint> newViewInfo)
	{
		viewInfo = newViewInfo;		
	}

}
