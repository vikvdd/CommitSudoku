package controllers;

import events.GameListener;
import events.GameStatListener;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import views.View;

public abstract class BaseController implements GameListener, GameStatListener{

	SudokuGame game;
	
	public BaseController(SudokuGame game) {
		this.game = game;
	}
	
	public void init()
	{
		initModel();
		initView();
		initController();
		initListeners();
		start();
	}
	
	protected abstract void initModel();
	
	protected abstract void initView();
	
	protected abstract void initController();
	
	protected void initListeners()
	{
		game.addGameListener(this);
		game.getStatTracker().addGameStatListener(this);
	}
	
	public abstract void start();
	
	
	
	@Override
	public void onGameStart(int[][] puzzleClone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNumberEntry(Coordinate coord, int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoteEntry(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleSolved(int[][] solution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNumberCompleted(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNumberUncompleted(int number) {
		// TODO Auto-generated method stub
		
	}
	
}
