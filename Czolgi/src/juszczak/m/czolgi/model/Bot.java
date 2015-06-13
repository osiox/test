package juszczak.m.czolgi.model;

import java.util.Random;

import juszczak.m.czolgi.model.obiekty.Czolg;
import juszczak.m.czolgi.movement.Direction;

public class Bot extends Gracz
{
	Random generator = new Random();
	int licznik;

	public Bot()
	{
		// TODO Auto-generated constructor stub
		czolg = new Czolg(false);
		licznik = 0;
	}
	
	public void move()
	{
		if(licznik == 0)
		{
			Direction mov = Direction.getByInt(generator.nextInt(5));
			super.setMovement(mov);
			super.setIsFiring(generator.nextBoolean());
		}
		licznik++;
		if(licznik == 10)
			licznik = 0;
		super.move();
	}

}
