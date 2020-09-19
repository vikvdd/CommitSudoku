package model.game.puzzle;

public class Coordinate {
	public final int x;
	public final int y;
	
	public Coordinate(int x,int y)
	{
		this.x = x; this.y = y;
	}
	
	public boolean isEqual(Coordinate coord)
	{
		if(coord.x == x && coord.y == y) return true;
		else return false;
	}
}
