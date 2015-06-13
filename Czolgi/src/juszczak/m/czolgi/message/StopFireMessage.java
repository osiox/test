package juszczak.m.czolgi.message;

import juszczak.m.czolgi.model.Model;

public class StopFireMessage extends Message
{
	@Override
	public void process(Model model)
	{
		model.stopPlayerFire();
	}
}
