package tests;

import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;

public class TestTing {
	
	public static void main(String[] args)
	{
		SudokuPuzzle puzzle = new SudokuPuzzle(new int[][]{
			{4,0,0,9,6,5,0,2,0},
			{1,0,0,0,0,0,4,0,0}, 
			{0,0,0,0,8,0,0,0,0},
			{0,0,0,2,0,0,0,4,0},
			{0,2,9,0,0,0,0,0,3},
			{0,0,0,5,3,0,6,0,0},
			{5,9,0,1,0,0,0,0,0}, 
			{0,0,0,0,0,7,5,0,0},
			{0,0,0,0,0,0,8,0,1}
		});
		
		//SudokuLogic.solve(puzzle);
		SudokuPuzzle genPuzzle = SudokuLogic.generateRandomPuzzle();
		genPuzzle.printPuzzle();
		SudokuLogic.solve(genPuzzle);
		
	}

}
