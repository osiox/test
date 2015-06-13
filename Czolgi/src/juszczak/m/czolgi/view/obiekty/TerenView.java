package juszczak.m.czolgi.view.obiekty;

import java.awt.Graphics;
import java.awt.Image;

import juszczak.m.czolgi.view.ResourcesGetter;

public class TerenView extends ObjectToPaint
{

	public TerenView(int posX, int posY, float posOffsetX, float posOffsetY)
	{
		super(posX, posY, posOffsetX, posOffsetY);
	}

	public void draw(Graphics g)
	{
		Image img = ResourcesGetter.getTerenImage();
		
		g.drawImage(img, realPosX, realPosY, null);
	}
	
}
