package juszczak.m.czolgi.message;

import juszczak.m.czolgi.model.Model;
import juszczak.m.czolgi.movement.Direction;

public class MovementMessage extends Message
{

	private Direction mov;
	
	public MovementMessage(Direction mov)
	{
		this.mov = mov;
	}

	@Override
	public void process(Model model)
	{
		model.setPlayerMovement(mov);
	}

}
