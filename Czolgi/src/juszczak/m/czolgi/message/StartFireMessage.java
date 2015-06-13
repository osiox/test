package juszczak.m.czolgi.message;

import juszczak.m.czolgi.model.Model;

public class StartFireMessage extends Message
{
	@Override
	public void process(Model model)
	{
		model.startPlayerFire();
	}
}
