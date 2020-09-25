package model.savesystem;

import java.io.File;

import model.game.puzzle.SudokuPuzzle;

public interface IGameDAO {
	public void savePuzzle(SudokuPuzzle puzzle);
	public SudokuPuzzle loadPuzzle(File fileName);
}
