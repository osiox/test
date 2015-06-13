package juszczak.m.czolgi.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import juszczak.m.czolgi.message.Message;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class View
{
	private Okno okienko;

	static ArrayList<BufferedImage> czolg = new ArrayList<BufferedImage>();
	static ArrayList<BufferedImage> pocisk = new ArrayList<BufferedImage>();
	static Image teren;

	public View(BlockingQueue<Message> msgQueue)
	{
		try
		{
			czolg.add(Direction.UP.getInt(),ImageIO.read(getClass().getResource("Resources/Czolg/czolg_gora.jpg")));
			czolg.add(Direction.DOWN.getInt(),ImageIO.read(getClass().getResource("Resources/Czolg/czolg_dol.jpg")));
			czolg.add(Direction.LEFT.getInt(),ImageIO.read(getClass().getResource("Resources/Czolg/czolg_lewo.jpg")));
			czolg.add(Direction.RIGHT.getInt(),ImageIO.read(getClass().getResource("Resources/Czolg/czolg_prawo.jpg")));
			
			pocisk.add(Direction.UP.getInt(),ImageIO.read(getClass().getResource("Resources/Pocisk/pocisk_gora.jpg")));
			pocisk.add(Direction.DOWN.getInt(),ImageIO.read(getClass().getResource("Resources/Pocisk/pocisk_dol.jpg")));
			pocisk.add(Direction.LEFT.getInt(),ImageIO.read(getClass().getResource("Resources/Pocisk/pocisk_lewo.jpg")));
			pocisk.add(Direction.RIGHT.getInt(),ImageIO.read(getClass().getResource("Resources/Pocisk/pocisk_prawo.jpg")));
		
			BufferedImage temp = ImageIO.read(getClass().getResource("Resources/Teren/Teren.jpg"));
			teren = temp.getScaledInstance((int)ObjectToPaint.getDefaultSize(), (int)ObjectToPaint.getDefaultSize(), Image.SCALE_SMOOTH);
		}
		catch(Exception e) {e.printStackTrace();}
		
		okienko = new Okno(msgQueue);
	}

	public void setViewInfo(LinkedList<ObjectToPaint> newViewInfo)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				okienko.setViewInfo(newViewInfo);
			}
		});
	}

	public void refresh()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				okienko.repaint();
			}
		});
	}
}
