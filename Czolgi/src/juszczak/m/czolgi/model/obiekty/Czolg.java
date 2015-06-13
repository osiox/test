package juszczak.m.czolgi.model.obiekty;

import juszczak.m.czolgi.movement.CollisionResult;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.view.obiekty.CzolgView;

public class Czolg extends ObiektGry
{	
	
	public Czolg(boolean isPlayer)
	{
		super.isPlayer = isPlayer;
	}
		
	public CzolgView paintType(int posX,int posY,float posOffsetX, float posOffsetY)
	{
		CzolgView cv = new CzolgView(posX,posY,posOffsetX, posOffsetY);
		cv.setDirection(dir);
		cv.setIsPlayer(isPlayer);
		return cv;
	}
	
	public Direction getDirection()
	{
		return dir;
	}
	
	public CollisionResult collisionType(ObiektGry obiekt)
	{
			return CollisionResult.MOVEMENT_NOT_ALLOWED;
	}

	public void destroy()
	{
		isDestroyed = true;
		
	}
}
