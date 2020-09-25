package model.game;

import java.util.List;

import events.GameModelEvent;
import events.GameStatListener;
import model.EntryType;
import model.game.actions.ActionLog;
import model.game.actions.PuzzleAction;
import model.game.puzzle.Coordinate;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.game.stats.GameStatTracker;
import util.Util;


public class SudokuGame extends GameModelEvent implements GameStatListener{
	private SudokuPuzzle puzzle;
	private ActionLog actionLog;
	private GameStatTracker statTracker;
	private GameTime gameTime;
	
	public SudokuGame()
	{
		super();
		actionLog = new ActionLog();
		statTracker = new GameStatTracker();
		puzzle = SudokuLogic.generateRandomPuzzle(45, 10);
	}
	
	public SudokuGame(SudokuPuzzle puzzle)
	{
		super();
		actionLog = new ActionLog();
		statTracker = new GameStatTracker();
		this.puzzle = puzzle;
	}
	
	public void loadNewGame(SudokuPuzzle puzzle)
	{
		this.puzzle = puzzle;
		start();
	}
	
	public void start()
	{
		actionLog.restartLog();
		statTracker.init(puzzle.getUserPuzzle());
		notifyGameStart(Util.clone2dArray(puzzle.getUserPuzzle()));
	}
	
	public void end()
	{
		notifyGameEnd();
	}
	
	public void enterNumber(Coordinate coord, int num)
	{
		int n = getEntryValue(coord, num);
		if(n == -1) return;
		PuzzleAction action = new PuzzleAction(coord, puzzle.getUserPuzzle()[coord.y][coord.x], n);
		actionLog.addAction(action);
		statTracker.addAction(action);
		puzzle.enterValue(n, coord);
		notifyNumberEntry(coord, n);
	}
	
	public void solve()
	{
		SudokuLogic.solve(puzzle);
		try {
			puzzle.setUserPuzzle(puzzle.getSolution(0));
			notifyPuzzleChanged(puzzle.getName(), puzzle.getDifficulty(), "000");
			//notifyPuzzleSolved(puzzle.getSolution(0));
		}
		catch (Exception e) {

		}
	}
	
	public void undoAction()
	{
		try {
			PuzzleAction action = actionLog.getAction();
			int val = action.getOldValue();
			Coordinate coord = action.getCoordinate();
			puzzle.enterValue(val,coord);
			actionLog.undoAction();
			notifyNumberEntry(coord, val);
		} catch (Exception e) {
		}
	}
	
	public void redoAction()
	{
		try {
			actionLog.redoAction();
			PuzzleAction action = actionLog.getAction();
			int val = action.getNewValue();
			Coordinate coord = action.getCoordinate();
			puzzle.enterValue(val,coord);
			notifyNumberEntry(coord, val);
		} catch (Exception e) {
		}
		
	}
	
	public SudokuPuzzle getPuzzle()
	{
		return puzzle;
	}
	
	public String getPuzzleName()
	{
		return puzzle.getName();
	} 
	
	public int getNumAtCoordinate(Coordinate coord)
	{
		return puzzle.getUserPuzzle()[coord.y][coord.x];
	}
	
	public EntryType getEntryType(Coordinate coord, int n)
	{
		int[][] puz = puzzle.get();
		int[][] userPuz = puzzle.getUserPuzzle();
		if(puz[coord.y][coord.x] != 0) return EntryType.FIXED;
		else if(SudokuLogic.possible(userPuz, coord.y, coord.x, n)) return EntryType.VALIDENTRY;
		else return EntryType.INVALIDENTRY;
		
	}
	
	//returns -1 if tile cant be changed, 0 if tile matches n, and n for standard entries
	private int getEntryValue(Coordinate coord, int n)
	{
		int[][] userPuz = puzzle.getUserPuzzle();
		int[][] puz = puzzle.get();
		if(puz[coord.y][coord.x] != 0) return -1;
		else if(userPuz[coord.y][coord.x] == n) return 0;
		else return n;
	}
	
	public List<Coordinate> getAllCoordinatesOfN(int n)
	{
		return SudokuLogic.findCoordinatesOfN(puzzle.getUserPuzzle(), n);
	}
	
	public GameStatTracker getStatTracker()
	{
		return statTracker;
	}
	
	public boolean isFixedTile(Coordinate coord)
	{
		if(puzzle.get()[coord.y][coord.x] != 0) return true;
		else return false;
	}
	
	public boolean isEntryTile(Coordinate coord)
	{
		return !isFixedTile(coord);
	}

	
	@Override
	public void onNumberCompleted(int number) {
		
	}

	
	@Override
	public void onPuzzleCompleted() {
		Util.println("PUZZLE COMPLETED!!!!");
		end();
		
	}
	
	
}
