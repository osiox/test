package juszczak.m.czolgi.model;

import java.util.ArrayList;
import java.util.LinkedList;

import juszczak.m.czolgi.controller.Refresh;
import juszczak.m.czolgi.movement.Direction;
import juszczak.m.czolgi.movement.Result;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class Model
{
	Gracz gracz;
	Refresh refresh;
	ArrayList<Bot> boty;
	Mapa mapa;
	boolean gameRunning;
	LinkedList<ObjectToPaint> viewInfo;
	
	public Model()
	{
		mapa = new Mapa();
		viewInfo = null;
		boty = new ArrayList<Bot>();
		gameRunning = false;
	}
	
	public void startGame()
	{
		gracz = new Gracz();
		boty.add(new Bot());
		boty.add(new Bot());
		boty.add(new Bot());
		mapa.spawnGracz(gracz);
		for(Bot b: boty)
			mapa.spawnBot(b);
		gameRunning = true;
	}
	public void setPlayerMovement(Direction mov)
	{
		gracz.setMovement(mov);
	}
	public void stopPlayerMovement(Direction mov)
	{
		gracz.stopMovement(mov);
	}
	public void setRefresh(Refresh r)
	{
		refresh = r;
	}
	public void calculate()
	{
		if(gracz.getCzolg().destroyed())
		{
			gracz = new Gracz();
			if(mapa.spawnGracz(gracz) == Result.FAILURE)
			{
				//Abort calculations
				gracz.getCzolg().destroy();
				return;
			}
		}
			gracz.move();
			LinkedList<Bot> temp = new LinkedList<Bot>();
			for(Bot b: boty)
			{
				if(b.getCzolg().destroyed())
				{
					temp.add(b);
				}
			}
			
			for(Bot b: temp)
			{
				boty.remove(b);
			}
			
			if(boty.size() < 3)
			{
				Bot b = new Bot();
				Result r = mapa.spawnBot(b);
				if(r == Result.SUCCESS)
				{
					boty.add(b);
				}
			}
			
			for(Bot b: boty)
				b.move();
			viewInfo = mapa.generateViewInfo();
			refresh.doRefresh();
	
	}

	public LinkedList<ObjectToPaint> getViewInfo()
	{
		return viewInfo;
	}

	public void startPlayerFire()
	{
		gracz.startFire();
	}

	public void stopPlayerFire()
	{
		gracz.stopFire();
		
	}

	public boolean gameOver()
	{
		return !gameRunning;
	}
}
