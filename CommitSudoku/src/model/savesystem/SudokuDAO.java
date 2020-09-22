package model.savesystem;

import java.io.File;

import model.game.puzzle.SudokuPuzzle;

public interface SudokuDAO {
	public void savePuzzle(File fileName);
	public SudokuPuzzle loadPuzzle(File fileName);
}
