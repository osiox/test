package juszczak.m.czolgi.model.obiekty;

import juszczak.m.czolgi.movement.CollisionResult;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.movement.Ruch;
import juszczak.m.czolgi.struktura.Wezel;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class ObiektGry
{
	Ruch ruchListener;
	float posOffset; // <-0.5, 0.5>
	protected Direction dir;
	float velocity;
	boolean isDestroyed;
	boolean isPlayer;
	boolean isTerrain;
	

	public ObiektGry()
	{
		posOffset = 0;
		velocity = 0.1f;
		dir = Direction.UP;
		isDestroyed = false;
		isPlayer = false;
		isTerrain = false;
	}
	
	private void setPosition(float posOffset)
	{
		this.posOffset = posOffset;
	}
	
	public void setListener(Ruch r)
	{
		ruchListener = r;
	}

	public void resetPosition()
	{
		posOffset = 0f;
	}
	public float calculateNewPosition()
	{
		if(dir == Direction.DOWN || dir == Direction.RIGHT)
			return posOffset + velocity;
		else
			return posOffset - velocity;
	}
	public void move(Direction dir)
	{
		if(isDestroyed)
			return;
		if(ruchListener == null)
			return;
		if (dir == Direction.NONE)
			return;
		
		if(this.dir != dir && this.dir != dir.getOppositeDirection())
			resetPosition();
		
		this.dir = dir;

		float tempPosOffset = calculateNewPosition();
		CollisionResult collisionResult = ruchListener.kolizja(dir);
		
		if(collisionResult == CollisionResult.BORDER)
		{
			if(causesDamage())
				collisionResult = CollisionResult.DAMAGE;
			else
				collisionResult = CollisionResult.MOVEMENT_NOT_ALLOWED;
		}
		
		if(collisionResult == CollisionResult.NO_COLLISION)
			if(tempPosOffset > 0.5f || tempPosOffset < -0.5f)
			{
				ruchListener.zmianaWezla(dir);
					if (dir == Direction.DOWN || dir == Direction.RIGHT)
						setPosition(tempPosOffset - 1);
					else
						setPosition(tempPosOffset + 1);
			}
			else
			{
				setPosition(tempPosOffset);
			}
		else if(collisionResult == CollisionResult.MOVEMENT_NOT_ALLOWED)
		{
			if (dir == Direction.DOWN || dir == Direction.RIGHT)
			{
				if(tempPosOffset < 0)
					setPosition(tempPosOffset);
				else
					resetPosition();
			}
			else
			{
				if(tempPosOffset > 0)
					setPosition(tempPosOffset);
				else
					resetPosition();
			}
		}
		else if(collisionResult == CollisionResult.DAMAGE)
			damageFrom(dir);
		else if(collisionResult == CollisionResult.DAMAGE_BY_PLAYER)
			damageFrom(dir, true);
		else if(collisionResult == CollisionResult.DAMAGE_BY_BOT)
			damageFrom(dir, false);
	}
	
	public void damageFrom(Direction dir)
	{
			ruchListener.explosion(posOffset);
			isDestroyed = true;
	}
	
	public void damageFrom(Direction dir,boolean byPlayer)
	{
		if(isTerrain)
		{
			damageFrom(dir);
		}
		else if(byPlayer != isPlayer)
		{
			damageFrom(dir);
		}
	}
	
	public CollisionResult collisionType(ObiektGry obiekt)
	{
			return CollisionResult.MOVEMENT_NOT_ALLOWED;	
	}
	public Boolean causesDamage()
	{
		return false;
	}

	public ObjectToPaint generateViewInfo(int posX, int posY)
	{
		float posOffsetX, posOffsetY;
		posOffsetX = posOffsetY = posOffset;
		
		if(dir == Direction.UP || dir == Direction.DOWN)
			posOffsetX = 0;
		else
			posOffsetY = 0;
		
		return paintType(posX,posY,posOffsetX, posOffsetY);
	}
	
	public ObjectToPaint paintType(int posX,int posY,float posOffsetX, float posOffsetY)
	{
		return new ObjectToPaint(posX,posY,posOffsetX, posOffsetY);
	}
	
	public Wezel getNeighbor(Direction dir)
	{
		return ruchListener.getNeighbor(dir);
	}
	
	public boolean destroyed()
	{
		return isDestroyed;
	}

	public boolean isPlayer()
	{
		return isPlayer;
	}
	public boolean isTerrain()
	{
		return isTerrain;
	}
}
