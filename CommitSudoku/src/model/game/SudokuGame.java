package model.game;

import java.util.List;

import events.GameListener;
import model.game.actions.ActionLog;
import model.game.actions.PuzzleAction;
import model.game.puzzle.Coordinate;
import model.game.puzzle.SudokuPuzzle;
import model.game.stats.GameStatTracker;

public class SudokuGame {
	private SudokuPuzzle puzzle;
	private ActionLog actionLog;
	private GameStatTracker statTracker;
	private GameTime gameTime;
	
	private List<GameListener> listeners;
	
	public SudokuGame()
	{
		actionLog = new ActionLog();
		statTracker = new GameStatTracker(puzzle.getUserPuzzle());
	}
	
	public void addGameListener(GameListener gameListener)
	{
		listeners.add(gameListener);
	}
	
	public void start()
	{
		statTracker.init();
	}
	
	public void end()
	{
		
	}
	
	public void enterNumber(Coordinate coord, int num)
	{
		if(num == -1) return;
		actionLog.addAction(new PuzzleAction(coord, puzzle.getUserPuzzle()[coord.y][coord.x], num));
		
	}
	
	/*public int getNumAtCoordinate(Coordinate coord)
	{
		//return puzzle.getUserPuzzle()
	}*/
	
	public void undoAction()
	{
		
	}
	
	public void redoAction()
	{
		
	}
	
	/*private NumTile findEntryType(Coordinate coord, int n)
	{
		int[][] puz = puzzle.get();
		int[][] userPuz = puzzle.getUserPuzzle();
		if(puz[coord.y][coord.x] == 0) return NumTile.Number;
		if
		
	}*/
	
	//returns -1 if tile cant be changed, 0 if tile matches n, and n for standard entries
	public int getEntryValue(Coordinate coord, int n)
	{
		int[][] userPuz = puzzle.getUserPuzzle();
		int[][] puz = puzzle.get();
		if(puz[coord.y][coord.x] == 0) return -1;
		else if(userPuz[coord.y][coord.x] == n) return 0;
		else return n;
	}
	
}
