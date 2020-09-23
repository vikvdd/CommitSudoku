package model.game.puzzle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import util.Util;

public class SudokuLogic {
	
	//fills puzzle solution list with all possible solutions
	public static void solve(SudokuPuzzle puzzle)
	{
		puzzle.setSolutions(new ArrayList<int[][]>());
		solveRec(puzzle, puzzle.get());
		//if(puzzle.getTotSolutions() > 0)
		return;
	}
	
	//recursively fills puzzle until completion and then adds to puzzle solutions, repeats
	private static void solveRec(SudokuPuzzle puz, int[][] sol)
	{	
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(sol[y][x] == 0)
				{
					for(int n = 1; n < 10; n++)
					{
						if(possible(sol, y, x, n)) 
						{
							sol[y][x] = n;
							solveRec(puz, sol);
							sol[y][x] = 0;
						}	
					}
					return;
				}
			}
		}
		
		puz.addSolution(Util.clone2dArray(sol));
		return;
	}
	
	
	//returns random puzzle using default values
	public static SudokuPuzzle generateRandomPuzzle()
	{	
		SudokuPuzzle puzzle = generateRandomPuzzle(45, 100);
		
		return puzzle;	
	}
	
	public static SudokuPuzzle generateRandomPuzzle(int removeTotal, int maxSolutions)
	{
		SudokuPuzzle puzzle = new SudokuPuzzle();
		List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9);
		generateRec(puzzle.get(), values);
		int maxAttemps = 100;
		Boolean validPuzzle = false;
		while(!validPuzzle|| maxAttemps>0)
		{
			solve(puzzle);
			if(puzzle.getTotSolutions() <= maxSolutions) 
			{
				validPuzzle = true;
			}
			maxAttemps--;
		}
		removeRandomValues(removeTotal, puzzle.get());
		puzzle.setUserPuzzle(Util.clone2dArray(puzzle.get()));
		
		return puzzle;
	}
	
	private static void generateRec(int[][] puz, List<Integer> values)
	{
		Collections.shuffle(values);
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(puz[y][x] == 0)
				{
					for(int i = 0; i < 9; i++)
					{
						if(possible(puz, y, x, values.get(i)))
						{
							puz[y][x] = values.get(i);
							generateRec(puz, values);
							if(puz[8][8] != 0) return;
							puz[y][x] = 0;
						}
					}
					return;
				}
			}
		}
		
		System.out.println("Generated new puzzle:");
	}
	
	private static void removeRandomValues(int total, int[][] puz)
	{
		int x; int y;
		Random rng = new Random();
		while(total > 0)
		{
			x = rng.nextInt(9);
			y = rng.nextInt(9);
			
			if(puz[y][x] != 0)
			{
				puz[y][x] = 0;
				total--;
			}	
		}
	}
	
	
	public static boolean possible(int[][] puzzle, int y, int x, int n)
	{
		for(int i = 0; i < 9; i++)
		{
			if(puzzle[y][i] == n && i != x) return false;
		}
		for(int i = 0; i < 9; i++)
		{
			if(puzzle[i][x] == n && i != y) return false;
		}
		Coordinate subGrid = getNearestSubGridCoordinate(new Coordinate(x, y));
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++) {
				if(puzzle[subGrid.y + i][subGrid.x + j] == n && (subGrid.y + i) != y && (subGrid.x + j) != x) return false;
			}
		}
		return true; 
	}
	
	public static List<Coordinate> findCoordinatesOfN(int[][] puzzle, int n)
	{
		List<Coordinate> total = new ArrayList<Coordinate>();
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(puzzle[y][x] == n) total.add(new Coordinate(x, y));
			}
		}
		
		return total;
	}
	
	public static Coordinate getNearestSubGridCoordinate(Coordinate coord)
	{
		int y0 = Math.floorDiv(coord.y, 3) * 3;
		int x0 = Math.floorDiv(coord.x, 3) * 3;
		return new Coordinate(x0, y0);
	}
	
}
