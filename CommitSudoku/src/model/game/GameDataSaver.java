package model.game;

import java.io.File;

import events.GameListener;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.savesystem.SudokuDAO;

public class GameDataSaver implements GameListener{
	SudokuDAO sudokuDAO;
	SudokuGame game;
	
	public GameDataSaver(SudokuDAO sudokuDAO) {
		this.sudokuDAO = sudokuDAO;
	}

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
		sudokuDAO.savePuzzle(new File(game.getPuzzleName() + ".txt"));
	}

	@Override
	public void onNumberEntry(Coordinate coord, int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
}
