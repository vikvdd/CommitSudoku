package model.savesystem;
import java.util.ArrayList;

import model.Difficulty;
import model.SudokuLogic;
import model.SudokuPuzzle;
import util.Util;

public class TestSave {
	public static void main(String[] args) {

		SudokuPuzzle puzzle1 = new SudokuPuzzle("BeginnerPuzzle", Difficulty.EASY, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle2 = new SudokuPuzzle("IntermediatePuzzle", Difficulty.MEDIUM, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle3 = new SudokuPuzzle("FuckyouPuzzle", Difficulty.HARD, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SaveManager.saveSudokuPuzzle(puzzle1, true);
		SaveManager.saveSudokuPuzzle(puzzle2, true);
		SaveManager.saveSudokuPuzzle(puzzle3, true);
		SudokuPuzzle test = SudokuLogic.generateRandomPuzzle(50, 1);
		SudokuLogic.solve(test);
		Util.println(test.getTotSolutions() + "");
	}
}
