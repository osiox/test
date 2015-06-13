package juszczak.m.czolgi.model.obiekty;

import juszczak.m.czolgi.movement.CollisionResult;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.view.obiekty.PociskView;

public class Pocisk extends ObiektGry
{
	public Pocisk(Direction dir, boolean isPlayer)
	{
		super.velocity = 0.6f;
		super.dir = dir;
		super.isPlayer = isPlayer;
	}
	
	public void move()
	{
		super.move(super.dir);
	}

	public Boolean causesDamage()
	{
		return true;
	}
	
	public PociskView paintType(int posX,int posY,float posOffsetX, float posOffsetY)
	{
		PociskView pv = new PociskView(posX,posY);
		pv.setDirection(dir);
		return pv;
	}
	
	public CollisionResult collisionType(ObiektGry obiekt)
	{
		if(causesDamage())
		{
			if(obiekt.causesDamage())
				return CollisionResult.DAMAGE;
			if(obiekt.isTerrain)
				return CollisionResult.DAMAGE;
			if(isPlayer)
				return CollisionResult.DAMAGE_BY_PLAYER;
			else
				return CollisionResult.DAMAGE_BY_BOT;
		}
		else
			return CollisionResult.MOVEMENT_NOT_ALLOWED;
			
	}
	
	public void damageFrom(Direction dir,boolean byPlayer)
	{
		damageFrom(dir);
	}

}
