package juszczak.m.czolgi.view.obiekty;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.view.ResourcesGetter;

public class PociskView extends ObjectToPaint
{

	private Direction dir;

	public PociskView(int posX, int posY)
	{
		super(posX, posY, 0, 0);
		dir = Direction.UP;
		float oldSize = super.size;
		size = 10f;
		realPosX += (int) (oldSize - size)/2;
		realPosY += (int) (oldSize - size)/2;
	}

	public void setDirection(Direction dir)
	{
		this.dir = dir;
	}
	
	public void draw(Graphics g)
	{
		BufferedImage bufferedImage = ResourcesGetter.getPociskImage(dir);
		
		Image img = bufferedImage.getScaledInstance((int)size, (int)size, Image.SCALE_SMOOTH);
				
		g.drawImage(img, realPosX, realPosY, null);
	}
	
}
