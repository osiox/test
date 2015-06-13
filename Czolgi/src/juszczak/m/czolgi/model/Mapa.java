package juszczak.m.czolgi.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import juszczak.m.czolgi.model.obiekty.ObiektGry;
import juszczak.m.czolgi.model.obiekty.Teren;
import juszczak.m.czolgi.movement.Result;
import juszczak.m.czolgi.struktura.Linnia;
import juszczak.m.czolgi.struktura.Wezel;
import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class Mapa
{
	private ArrayList<Linnia> wiersze,kolumny;

	public Mapa()
	{
		wiersze = new ArrayList<Linnia>(25);
		kolumny = new ArrayList<Linnia>(25);
		
		for(int i = 0; i<25;i++)
		{
			wiersze.add(new Linnia());
			kolumny.add(new Linnia());
		}
		
		for(int w = 0; w<25; w++)
		{
			for(int k = 0; k<25; k++)
			{
				Linnia wiersz = wiersze.get(w);
				Linnia kolumna = kolumny.get(k);
				
				Wezel temp = new Wezel();
				temp.setPos(k,w);
				temp.addKolumna(kolumna);
				temp.addWiersz(wiersz);
				wiersz.addWezel(temp);
				kolumna.addWezel(temp);
			}
		}
	initiate();
	}
	
	public void initiate()
	{
		Random gen = new Random();
		
		for(int j = 1;j<25;j++)
			for(int i = 0; i<25; i+=gen.nextInt(3))
			{
				if(i != 12 && j != 24)
				{
					Teren dk = new Teren();
					spawn(dk,i,j);
				}
			}
	}
	
	public LinkedList<ObjectToPaint> generateViewInfo()
	{
		LinkedList<ObjectToPaint> viewInfo = new LinkedList<ObjectToPaint>();
		
		for(Linnia l: wiersze)
		{
			viewInfo.addAll(l.generateViewInfo());
		}
		
		return viewInfo;
	}
		
	public Result spawnGracz(Gracz g)
	{
		return spawn(g.getCzolg(),10,24);
	}
	
	public Result spawnBot(Bot b)
	{
		Result r = Result.FAILURE;
		
		for(int i=0;i<25;i+=12)
		{
			r = spawn(b.getCzolg(),i,0);
			if(r == Result.SUCCESS)
				return r;
		}
		return r;
	}
	
	public Result spawn(ObiektGry o, int X, int Y)
	{
		Wezel wezel = wiersze.get(Y).getWezel(X);
		if(wezel.hasObiekt())
			return Result.FAILURE;
		else
		{
			wezel.addObiekt(o);
			return Result.SUCCESS;
		}
	}

}
