package juszczak.m.czolgi.model.obiekty;

import juszczak.m.czolgi.view.obiekty.TerenView;

public class Teren extends ObiektGry
{

	public Teren()
	{
		isTerrain = true;
	}
	
	public TerenView paintType(int posX,int posY,float posOffsetX, float posOffsetY)
	{
		TerenView tv = new TerenView(posX,posY,posOffsetX, posOffsetY);
		return tv;
	}	
}
