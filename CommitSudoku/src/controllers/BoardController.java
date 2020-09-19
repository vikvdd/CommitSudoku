package controllers;

import events.GameListener;
import model.EntryType;
import model.game.SudokuGame;

public class BoardController implements GameListener{
	
	SudokuGame game;
	
	public BoardController(SudokuGame game)
	{
		
	}
	
	private void initBoard()
	{
		
	}

	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNumberEntry(int number) {
		
		
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
	
	/*private void fillGameboard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				int[][] puzzle = model.getPuzzle().getUserPuzzle();
				int val = puzzle[y][x];
				puzzle[y][x] = 0;				
				EntryType tileType = findNumTileType(y, x);
				setButtonText(val, y, x, tileType);
			}	
		}
	}*/
}
