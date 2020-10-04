package model.game;

import java.util.ArrayList;
import java.util.List;
import events.GameModelEvent;
import events.GameStatListener;
import model.EntryType;
import model.game.actions.ActionLog;
import model.game.actions.PuzzleAction;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.Notes;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.game.stats.GameStatTracker;
import util.Util;


public class SudokuGame extends GameModelEvent implements GameStatListener{
	private SudokuPuzzle puzzle;
	private ActionLog actionLog;
	private GameStatTracker statTracker;
	private GameTime gameTime;
	private Coordinate selectedCoord;
	
	public SudokuGame()
	{
		super();
		actionLog = new ActionLog();
		statTracker = new GameStatTracker();
		puzzle = SudokuLogic.generateRandomPuzzle(45, 10);
		selectedCoord = new Coordinate(0, 0);
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
		notifyPuzzleChanged(puzzle.getName(), puzzle.getDifficulty(), "000");
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
	
	public void reset()
	{
		puzzle.setUserPuzzle(Util.clone2dArray(puzzle.get()));
		notifyPuzzleChanged(puzzle.getName(), puzzle.getDifficulty(), "000");
	}
	
	public void enterNumber(int num)
	{
		int n = getEntryValue(selectedCoord, num);
		if(n == -1) return;
		PuzzleAction action = new PuzzleAction(selectedCoord, puzzle.getUserPuzzle()[selectedCoord.y][selectedCoord.x], n);
		actionLog.addAction(action);
		statTracker.addAction(action);
		puzzle.enterValue(n, selectedCoord);
		getNotesAtCoordinate(selectedCoord).clearAllNotes();
		updateOpposingNotes(selectedCoord, n);
		notifyNumberEntry(selectedCoord, n);
	}
	
	public void enterNote(int num)
	{
		puzzle.enterNote(num, selectedCoord);
		notifyNoteEntry(num);
	}
	
	public void solve()
	{
		SudokuLogic.solve(puzzle);
		try {
			puzzle.setUserPuzzle(puzzle.getSolution(0));
			notifyPuzzleChanged(puzzle.getName(), puzzle.getDifficulty(), "000");
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
	
	public Difficulty getPuzzleDifficulty()
	{
		return puzzle.getDifficulty();
	}
	
	public int getNumAtCoordinate(Coordinate coord)
	{
		return puzzle.getUserPuzzle()[coord.y][coord.x];
	}
	
	public Notes getNotesAtCoordinate(Coordinate coord)
	{
		Notes notes = new Notes();
		try {
			notes = puzzle.getAllNotes()[coord.y][coord.x];
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return notes;
	}
	
	public void updateOpposingNotes(Coordinate coord, int n)
	{
		for(Coordinate c : getAllOpposingCoord(coord))
		{
			getNotesAtCoordinate(c).setNoteStatus(n, false);
		}
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
	
	public List<Coordinate> getAllOpposingCoord(Coordinate coord)
	{
		List<Coordinate> coords = getColCoordsAtCoord(coord);
		coords.addAll(getRowCoordsAtCoord(coord));
		coords.addAll(getSubGridCoordsAtCoord(coord));
		
		return coords;
	}
	
	public List<Coordinate> getRowCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		for (int x = 0; x < 9; x++) {
			if(coord.x != x) coords.add(new Coordinate(x, coord.y));
		}
		return coords;
	}
	
	public List<Coordinate> getColCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		for (int y = 0; y < 9; y++) {
			if(coord.y != y) coords.add(new Coordinate(coord.x, y));	
		}
		return coords;
	}
	
	public List<Coordinate> getSubGridCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		Coordinate subCoord = SudokuLogic.getNearestSubGridCoordinate(coord);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if(coord.y != subCoord.y + y && coord.x != subCoord.x + x)
					coords.add(new Coordinate(x + subCoord.x, y + subCoord.y));
			}
		}
		return coords;
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
	public void onNumberUncompleted(int number) {		
	}
	
	@Override
	public void onPuzzleCompleted() {
		Util.println("PUZZLE COMPLETED!!!!");
		end();
		
	}
	
	private void updateNotesForCoord(int n, Coordinate coord)
	{
		List<Coordinate> col = getColCoordsAtCoord(coord);
		List<Coordinate> row = getRowCoordsAtCoord(coord);
		List<Coordinate> sub = getSubGridCoordsAtCoord(coord);
		
		updateNotesAtCoords(n,col);
		updateNotesAtCoords(n,row);
		updateNotesAtCoords(n,sub);
	}
	
	private void updateNotesAtCoords(int n, List<Coordinate> coords)
	{
		Util.println(coords.get(0) + ":::");
		for(Coordinate coord: coords)
		{
			Notes notes = getNotesAtCoordinate(coord);
			notes.setNoteStatus(n, false);
		}
	}
	
	public void setSelectedCoord(Coordinate coord)
	{
		selectedCoord = new Coordinate(coord.x, coord.y);
	}
	
	public Coordinate getSelectedCoord()
	{
		return selectedCoord;
	}
}
