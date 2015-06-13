package juszczak.m.czolgi.view;

import java.awt.Image;
import java.awt.image.BufferedImage;

import juszczak.m.czolgi.movement.Direction;

public class ResourcesGetter
{
	public static BufferedImage getCzolgImage(Direction dir)
	{
		return View.czolg.get(dir.getInt());
	}
	
	public static BufferedImage getPociskImage(Direction dir)
	{
		return View.pocisk.get(dir.getInt());
	}
	
	public static Image getTerenImage()
	{
		return View.teren;
	}
}
