package juszczak.m.czolgi.model;

import juszczak.m.czolgi.model.obiekty.Czolg;
import juszczak.m.czolgi.model.obiekty.Pocisk;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.struktura.Wezel;

public class Gracz
{
	Czolg czolg;
	Direction movement;
	boolean isFiring;
	Pocisk pocisk;

	public Gracz()
	{
		movement = Direction.NONE;
		czolg = new Czolg(true);
		isFiring = false;
		pocisk = null;
	}
	
	public Czolg getCzolg()
	{
		return this.czolg;
	}
	public void move()
	{
		if(getCzolg().destroyed())
		{
			if(pocisk != null)
			{
				pocisk = null;
			}
		}
		else
		{
			if(pocisk!=null && pocisk.destroyed())
				pocisk = null;
			if(isFiring)
				fire(czolg.getDirection());
			if(pocisk != null)
				pocisk.move();
			czolg.move(movement);
		}
	}
	private void fire(Direction dir)
	{
		if(pocisk == null)
		{
			pocisk = new Pocisk(dir,getCzolg().isPlayer());
			Wezel neighbor = czolg.getNeighbor(dir);
			if(neighbor != null)
			{
				if(neighbor.hasObiekt())
				{
					neighbor.damageObiekt(dir,czolg.isPlayer());
					pocisk = null;
				}
				else
				{
					neighbor.addObiekt(pocisk);
					pocisk.setListener(neighbor);
				}
			}
			else
				pocisk = null;
		}
	}

	public void setMovement(Direction mov)
	{
		movement = mov;
	}

	public void stopMovement(Direction mov)
	{
		if(movement == mov)
			movement = Direction.NONE;
	}

	public void startFire()
	{
		setIsFiring(true);
		
	}

	public void stopFire()
	{
		setIsFiring(false);
		
	}

	public void setIsFiring(boolean isFiring)
	{
		this.isFiring = isFiring;
		
	}
}
