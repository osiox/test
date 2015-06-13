package juszczak.m.czolgi.movement;

public enum Direction
{
	NONE,
	UP,
	DOWN,
	LEFT,
	RIGHT,
	LEFT_UP,
	RIGHT_UP,
	LEFT_DOWN,
	RIGHT_DOWN;
	
	private Direction opposite;
	private int toInt;

    static
    {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
        NONE.opposite = NONE;
        LEFT_UP.opposite = RIGHT_DOWN;
    	RIGHT_UP.opposite = LEFT_DOWN;
    	LEFT_DOWN.opposite = RIGHT_UP;
    	RIGHT_DOWN.opposite = LEFT_UP;
        
    }
    
    static
    {
    	NONE.toInt = 0;
    	UP.toInt = 0;
    	DOWN.toInt = 1;
    	LEFT.toInt = 2;
    	RIGHT.toInt = 3;
    }
    
    public Direction getOppositeDirection()
    {
        return opposite;
    }
    
    public int getInt()
    {
    	return toInt;
    }
    
    public static Direction getByInt(int i)
    {
    	switch(i)
    	{
    	case 0:
    		return Direction.UP;
    	case 1:
    		return Direction.DOWN;
    	case 2:
    		return Direction.LEFT;
    	case 3:
    		return Direction.RIGHT;
    	default:
    		return Direction.NONE;
    	}
    }
}
