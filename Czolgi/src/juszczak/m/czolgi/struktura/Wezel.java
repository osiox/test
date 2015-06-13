package juszczak.m.czolgi.struktura;

import java.util.Collection;
import java.util.LinkedList;

import juszczak.m.czolgi.model.obiekty.ObiektGry;
import juszczak.m.czolgi.movement.CollisionResult;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.movement.Ruch;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class Wezel implements Ruch
{
	private ObiektGry obiekt;
	private Neighbors wiersz,kolumna;
	int posX,posY;
	
	public Wezel()
	{
		obiekt = null;
	}
	
	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public void addObiekt(ObiektGry obiekt)
	{
		this.obiekt = obiekt;
		obiekt.setListener(this);
	}
	
	public void removeObiekt()
	{
		if(obiekt != null)
			obiekt.setListener(null);
		obiekt = null;
	}
	
	public boolean hasObiekt()
	{
		return (obiekt != null);
	}
	
	public void addWiersz(Neighbors wiersz)
	{
		this.wiersz = wiersz;
	}
	
	public void addKolumna(Neighbors kolumna)
	{
		this.kolumna = kolumna;
	}

	@Override
	public Wezel getNeighbor(Direction dir)
	{
		if(dir == Direction.UP || dir == Direction.DOWN)
		{
			if(dir == Direction.UP)
				return kolumna.getPrev(this);
			else
				return kolumna.getNext(this);
		}
		else
		{
			if(dir == Direction.LEFT)
				return wiersz.getPrev(this);
			else
				return wiersz.getNext(this);
		}
	}
	
	@Override
	public CollisionResult kolizja(Direction dir)
	{
		Wezel wezelKolizji = getNeighbor(dir);
		if(wezelKolizji == null)
			return CollisionResult.BORDER;
		return kolizja(dir, wezelKolizji);
	}
	
	private CollisionResult kolizja(Direction dir, Wezel wezelKolizji)
	{
		if(wezelKolizji.obiekt == null/* && wezelKolizji.pusteZKierunku(dir)*/)
			return CollisionResult.NO_COLLISION;
		if(wezelKolizji.obiekt != null)
		{
			CollisionResult cResult = obiekt.collisionType(wezelKolizji.obiekt);
			if(cResult == CollisionResult.DAMAGE)
			{
				wezelKolizji.obiekt.damageFrom(dir.getOppositeDirection());
			}
			else if(cResult == CollisionResult.DAMAGE_BY_PLAYER/* && !wezelKolizji.obiekt.isPlayer()*/)
				wezelKolizji.obiekt.damageFrom(dir.getOppositeDirection(),true);
			else if(cResult == CollisionResult.DAMAGE_BY_BOT/* && wezelKolizji.obiekt.isPlayer()*/)
				wezelKolizji.obiekt.damageFrom(dir.getOppositeDirection(),false);
			return cResult;
		}
		return CollisionResult.MOVEMENT_NOT_ALLOWED;		
	}
		
	@Override
	public void zmianaWezla(Direction dir)
	{
		Wezel newWezel = getNeighbor(dir);
		ObiektGry temp = obiekt;
		removeObiekt();
		newWezel.addObiekt(temp);
	}
	
	@Override
	public void explosion(float posOffset)
	{
		removeObiekt();		
	}
	public Collection<? extends ObjectToPaint> generateViewInfo()
	{
		LinkedList<ObjectToPaint> viewInfo = new LinkedList<ObjectToPaint>();
		
		if(obiekt != null)
		{
			ObjectToPaint temp = obiekt.generateViewInfo(posX,posY);
			if(temp != null)
				viewInfo.add(temp);
		}
		
		return viewInfo;
	}

	public void damageObiekt(Direction dir, boolean byPlayer)
	{
		obiekt.damageFrom(dir,byPlayer);
		
	}
}
