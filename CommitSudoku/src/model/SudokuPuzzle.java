package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class SudokuPuzzle implements Serializable{
	
	private static final long serialVersionUID = 8341359791475694989L;
	
	private String name; 
	private Difficulty difficulty;
	private int[][] puzzle;
	private int[][] userPuzzle;
	private List<int[][]> solutions;
	private int totalSolutions;
	private Boolean solved;
	
	public SudokuPuzzle()
	{
		puzzle = new int[9][9];
		userPuzzle = new int[9][9];
		solutions = new ArrayList<int[][]>();
		totalSolutions = 0;
		difficulty = Difficulty.UNSOLVED;
	}
	
	public SudokuPuzzle(int[][] _puzzle)
	{
		puzzle = _puzzle;
		userPuzzle = Util.Clone2dArray(_puzzle);
		solutions = new ArrayList<int[][]>();
		totalSolutions = 0;
		difficulty = Difficulty.UNSOLVED;
	}
	
	public SudokuPuzzle(String name, Difficulty difficulty, int[][] puzzle, int[][] userPuzzle, List<int[][]> solutions, int totalSolutions, Boolean solved)
	{
		this.name = name;
		this.difficulty = difficulty;
		this.puzzle = puzzle;
		this.userPuzzle = userPuzzle;
		this.solutions = solutions;
		this.totalSolutions = totalSolutions;
		this.solved = solved;
	}
	
	public void enterValue(int val, int y, int x)
	{
		puzzle[y][x] = val;
		userPuzzle[y][x] = val;
	}
	
	public int[][] get()
	{
		return puzzle;
	}
	
	public int getValue(int y, int x)
	{
		return puzzle[y][x];
	}
	
	public void setUserPuzzle(int[][] _puzzle)
	{
		userPuzzle = _puzzle;
	}
	
	public int[][] getUserPuzzle()
	{
		return Util.Clone2dArray(userPuzzle);
	}
	
	public int[][] getSolution(int index)
	{
		if (solutions.size() > 0)
			return Util.Clone2dArray(solutions.get(index));
		else return null;
	}
	
	public List<int[][]> getAllSolutions()
	{
		return solutions;
	}
	
	public int getTotSolutions()
	{
		totalSolutions = solutions.size();
		return totalSolutions;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Difficulty getDifficulty()
	{
		return difficulty;
	}
	
	public Boolean isSolved()
	{
		return solved;
	}
	
	
	public void setPuzzle(int[][] _puzzle)
	{
		puzzle = _puzzle;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDifficulty(Difficulty difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setSolutions(List<int[][]> solutions)
	{
		this.solutions = solutions;
	}
	
	public void addSolution(int[][] _solution)
	{
		solutions.add(_solution);
		totalSolutions = solutions.size();
	}
	
	public void verifyPuzzle()
	{
		for(int[][] sol : solutions)
		{
			if(solved = false)
			{
				solved = comparePuzzles(puzzle, sol);
			}
		}
		solved = true;
	}
	
	public void printPuzzle()
	{	
		String str = "";
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				str = str + puzzle[y][x] + "  ";
			}
			str = str + "\n";
		}
		System.out.print(str);
		System.out.println("-------------------------------------");
	}
	
	private Boolean comparePuzzles(int[][] puzzle1, int[][] puzzle2)
	{
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(puzzle1[y][x] != puzzle2[y][x]) return false;
			}
		}
		return true;
	}
}
