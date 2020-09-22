package model.savesystem;

import java.io.File;

import model.game.puzzle.SudokuPuzzle;

public interface SudokuDAO {
	public void savePuzzle(SudokuPuzzle puzzle);
	public SudokuPuzzle loadPuzzle(File fileName);
}
