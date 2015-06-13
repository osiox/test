package juszczak.m.czolgi.movement;

import juszczak.m.czolgi.struktura.Wezel;


public interface Ruch
{
	public void zmianaWezla(Direction dir);
	public CollisionResult kolizja(Direction dir);
	public void explosion(float posOffset);
	public Wezel getNeighbor(Direction dir);
}
