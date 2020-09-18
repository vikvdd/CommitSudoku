package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStatTracker {
	private GameModel model;
	private List<List<Coordinate>> numbers;
	
	private List<Coordinate> num1;
	private List<Coordinate> num2;
	private List<Coordinate> num3;
	private List<Coordinate> num4;
	private List<Coordinate> num5;
	private List<Coordinate> num6;
	private List<Coordinate> num7;
	private List<Coordinate> num8;
	private List<Coordinate> num9;
	
	public GameStatTracker(GameModel model)
	{
		this.model = model;
		num1 = new ArrayList<Coordinate>();
		num2 = new ArrayList<Coordinate>();
		num3 = new ArrayList<Coordinate>();
		num4 = new ArrayList<Coordinate>();
		num5 = new ArrayList<Coordinate>();
		num6 = new ArrayList<Coordinate>();
		num7 = new ArrayList<Coordinate>();
		num8 = new ArrayList<Coordinate>();
		num9 = new ArrayList<Coordinate>();
		numbers = Arrays.asList(num1,num2,num3,num4,num5,num6,num7,num8,num9);
		
		init(model.getPuzzle().getUserPuzzle());
	}
	
	private void init(int[][] userPuz)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if(userPuz[y][x] != 0)
				{
					numbers.get(userPuz[y][x]-1).add(new Coordinate(x, y));
				}
			}
		}
	}
	
	public void addCoordinate(int y, int x, int val) throws IndexOutOfBoundsException
	{
		int[][] userPuz = model.getPuzzle().getUserPuzzle();
		int index = val-1;
		if(val > 0) addCoordinate(numbers.get(index), new Coordinate(x, y));
		else if(val == 0) 
		{
			int numIndex = userPuz[y][x]-1;
			if(numIndex > 0) removeCoordinate(numbers.get(numIndex), new Coordinate(x,y));
		}
	}
	
	private void addCoordinate(List<Coordinate> num, Coordinate coord)
	{
		for(Coordinate coordinate : num)
		{
			if (coordinate.isEqual(coord)) return;
		}
		num.add(coord);
		return;
	}
	
	private void removeCoordinate(List<Coordinate> num, Coordinate coord)
	{
		int[][] userPuz = model.getPuzzle().getUserPuzzle();
		for(Coordinate coordinate : num)
		{
			if (userPuz[coord.y][coord.x] == 0) num.remove(coordinate);
		}
		return;
	}
	
}
