package model;

import util.Util;

import java.util.ArrayList;
import java.util.List;


import events.ModelChangeObserver;

public class GameModel{
	private SudokuPuzzle puzzle;
	float startTime;
	float completionTime;
	private List<ModelChangeObserver> observers;
	
	public GameModel()
	{
		observers = new ArrayList<ModelChangeObserver>();
	}
	
	public void attach(ModelChangeObserver observer)
	{
		observers.add(observer);
	}
	
	public void start()
	{
		puzzle.setUserPuzzle(Util.Clone2dArray(puzzle.get()));
		startTime = System.currentTimeMillis();
		Util.println("Started puzzle at: " + startTime);
	}
	
	public SudokuPuzzle getPuzzle()
	{
		return puzzle;
	}
	
	public void setGamePuzzle(SudokuPuzzle puzzle)
	{
		this.puzzle = puzzle;
		notifyAllObservers();
	}
	
	public void setUserPuzzle(int[][] puz)
	{
		puzzle.setUserPuzzle(puz);
		notifyAllObservers();
	}

	public void updateUserPuzzle(int val, int y, int x)
	{
		int[][] puz = puzzle.getUserPuzzle();
		puz[y][x] = val;
		puzzle.setUserPuzzle(puz);
		notifyAllObservers();
	}
	
	public void setRandomPuzzle()
	{
		puzzle = SudokuLogic.generateRandomPuzzle();
	}
	
	public void notifyAllObservers()
	{
		for(ModelChangeObserver observer : observers)
		{	
			observer.update();
		}
	}

}
