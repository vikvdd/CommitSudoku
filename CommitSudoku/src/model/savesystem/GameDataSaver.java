package model.savesystem;

import events.GameListener;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import util.Util;

public class GameDataSaver implements GameListener{
	
	
	IGameDAO sudokuDAO;
	SudokuGame game;
	
	public GameDataSaver(IGameDAO sudokuDAO, SudokuGame game) {
		this.sudokuDAO = sudokuDAO;
		this.game = game;
		game.addGameListener(this);
	}

	@Override
	public void onGameStart(int[][] puzzleClone) {		
	}

	@Override
	public void onGameEnd() {
		sudokuDAO.savePuzzle(game.getPuzzle());
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		Util.println("Goon laddy");
		sudokuDAO.savePuzzle(game.getPuzzle());
	}

	@Override
	public void onNumberEntry(Coordinate coord, int number) {
		
	}

	@Override
	public void onPuzzleCompleted() {
		sudokuDAO.savePuzzle(game.getPuzzle());
	}

	@Override
	public void onPuzzleSolved(int[][] solution) {
		sudokuDAO.savePuzzle(game.getPuzzle());
	}
}
