package model;

public class PuzzleAction {
	Coordinate coordinate;
	int oldValue;
	int newValue;
	
	public PuzzleAction(Coordinate coordinate, int oldValue, int newValue)
	{
		this.coordinate = coordinate;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	public Coordinate getCoordinate()
	{
		return coordinate;
	}
	
	public int getOldValue()
	{
		return oldValue;
	}
	
	public int getNewValue()
	{
		return newValue;
	}
}
