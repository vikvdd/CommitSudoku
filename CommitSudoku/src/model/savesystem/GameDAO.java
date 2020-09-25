package model.savesystem;

import java.io.File;

import model.game.puzzle.SudokuPuzzle;
import util.Util;

public class GameDAO implements IGameDAO {

	@Override
	public void savePuzzle(SudokuPuzzle puzzle) {
		Util.println("saving atleast");
		SaveManager.savePuzzle(puzzle, true);
	}

	@Override
	public SudokuPuzzle loadPuzzle(File fileName) {
		SudokuPuzzle puzzle = SaveManager.loadPuzzle(fileName);
		return puzzle;
	}

	

}
