package tests;
import java.util.ArrayList;

import model.game.puzzle.Difficulty;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;

public class TestSave {
	public static void main(String[] args) {

		SudokuPuzzle puzzle1 = new SudokuPuzzle("BeginnerPuzzle", Difficulty.EASY, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle2 = new SudokuPuzzle("IntermediatePuzzle", Difficulty.MEDIUM, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SudokuPuzzle puzzle3 = new SudokuPuzzle("FuckyouPuzzle", Difficulty.HARD, SudokuLogic.generateRandomPuzzle().get(), SudokuLogic.generateRandomPuzzle().get(), new ArrayList<int[][]>(), 1,false);
		SaveManager.saveSudokuPuzzle(puzzle1, true);
		SaveManager.saveSudokuPuzzle(puzzle2, true);
		SaveManager.saveSudokuPuzzle(puzzle3, true);
		Util.println(PuzzleSaveList.getInstance().getSaveList().get(0).getName() + "");
		
		for(String save : PuzzleSaveList.getInstance().getListAsStrings())
		{
			Util.println(save + "----");
		}
		//SudokuPuzzle test = SudokuLogic.generateRandomPuzzle(50, 1);
		//SudokuLogic.solve(test);
		//Util.println(test.getTotSolutions() + "");
	}
}
