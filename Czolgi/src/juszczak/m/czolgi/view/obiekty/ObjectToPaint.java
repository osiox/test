package juszczak.m.czolgi.view.obiekty;

import java.awt.Color;
import java.awt.Graphics;

public class ObjectToPaint
{
	final static float defaultSize = 30f;
	
	protected int posX, posY;
	protected int realPosX, realPosY;
	protected float posOffsetX, posOffsetY;
	protected float size;
	
	public ObjectToPaint(int posX, int posY, float posOffsetX, float posOffsetY)
	{
		this.posX = posX;
		this.posY = posY;
		this.posOffsetX = posOffsetX;
		this.posOffsetY = posOffsetY;
		size = defaultSize;
		realPosX = (int) (posX*size + posOffsetX*size);
		realPosY = (int) (posY*size + posOffsetY*size);
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(realPosX, realPosY, (int)size, (int)size);
	}
	
	public static float getDefaultSize()
	{
		return defaultSize;
	}
}
