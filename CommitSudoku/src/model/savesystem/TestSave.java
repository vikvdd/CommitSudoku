package model.savesystem;
import java.util.ArrayList;

import model.game.puzzle.Difficulty;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import util.Util;

public class TestSave {
	public static void main(String[] args) {

		SudokuPuzzle puzzle1 = new SudokuPuzzle("BeginnerPuzzle", Difficulty.EASY, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle2 = new SudokuPuzzle("IntermediatePuzzle", Difficulty.MEDIUM, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle3 = new SudokuPuzzle("FuckyouPuzzle", Difficulty.HARD, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SaveManager.savePuzzle(puzzle1, true);
		SaveManager.savePuzzle(puzzle2, true);
		SaveManager.savePuzzle(puzzle3, true);
		SudokuPuzzle test = SudokuLogic.generateRandomPuzzle(50, 1);
		SudokuLogic.solve(test);
		Util.println(test.getTotSolutions() + "");
	}
}
