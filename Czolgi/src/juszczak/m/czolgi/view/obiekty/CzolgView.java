package juszczak.m.czolgi.view.obiekty;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.view.ResourcesGetter;

public class CzolgView extends ObjectToPaint
{

	private Direction dir;
	private boolean isPlayer;
	
	public CzolgView(int posX, int posY, float posOffsetX, float posOffsetY)
	{
		super(posX, posY, posOffsetX, posOffsetY);
		dir = Direction.UP;
		isPlayer = true;
	}
	
	public void setDirection(final Direction dir)
	{
		this.dir = dir;
	}
	
	public void draw(final Graphics g)
	{
		BufferedImage bufferedImage = ResourcesGetter.getCzolgImage(dir);
		if(!isPlayer)
		{
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
			ColorConvertOp op = new ColorConvertOp(cs, null);  
			bufferedImage = op.filter(bufferedImage, null); 
		}
		
		Image img = bufferedImage.getScaledInstance((int)size, (int)size, Image.SCALE_SMOOTH);
				
		g.drawImage(img, realPosX, realPosY, null);
	}

	public void setIsPlayer(final boolean isPlayer)
	{
		this.isPlayer = isPlayer;		
	}

}
