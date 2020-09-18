package model;

public class SudokuGame {
	private SudokuPuzzle puzzle;
	private ActionLog actionLog;
	private GameStatTracker statTracker;
	private GameTime gameTime;
	
	public SudokuGame()
	{
		
	}
	
	public void start()
	{
		actionLog = new ActionLog();
		
	}
	
}
