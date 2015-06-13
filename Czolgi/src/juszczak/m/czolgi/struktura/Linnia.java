package juszczak.m.czolgi.struktura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import juszczak.m.czolgi.view.obiekty.ObjectToPaint;

public class Linnia implements Neighbors
{
	private ArrayList<Wezel> wezly;
	
	public Linnia()
	{
		wezly = new ArrayList<Wezel>(25);
	}
	
	public void addWezel(Wezel wezel)
	{
		wezly.add(wezel);
	}
	
	public Wezel getWezel(int i)
	{
		return wezly.get(i);
	}

	@Override
	public Wezel getPrev(Wezel wezel)
	{
		int index = wezly.indexOf(wezel);
		
		if(index != 0)
			return wezly.get(index-1);
		else
			return null;
	}

	@Override
	public Wezel getNext(Wezel wezel)
	{
		int index = wezly.indexOf(wezel);
		
		if(index != wezly.size()-1)
			return wezly.get(index+1);
		else
			return null;
	}

	public Collection<? extends ObjectToPaint> generateViewInfo()
	{
		LinkedList<ObjectToPaint> viewInfo = new LinkedList<ObjectToPaint>();
		for(Wezel wezel: wezly)
			viewInfo.addAll(wezel.generateViewInfo());
		
		return viewInfo;
	}

}
