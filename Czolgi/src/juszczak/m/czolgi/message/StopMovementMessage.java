package juszczak.m.czolgi.message;

import juszczak.m.czolgi.model.Model;
import juszczak.m.czolgi.movement.Direction;

public class StopMovementMessage extends Message
{

	private Direction mov;
	
	public StopMovementMessage(Direction mov)
	{
		this.mov = mov;
	}

	@Override
	public void process(Model model)
	{
		model.stopPlayerMovement(mov);
		
	}

}
