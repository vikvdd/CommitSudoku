package model.game.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.game.actions.PuzzleAction;

public class GameStatTracker{

	List<Integer> total = new ArrayList<Integer>();
	int t0;
	int t1;
	int t2;
	int t3;
	int t4;
	int t5;
	int t6;
	int t7;
	int t8;
	int t9;
	int totalFilledSpaces;
	int totalEmptySpaces;
	int mistakes;
	
	public GameStatTracker()
	{
	}
	
	public void init(int[][] puzzle)
	{
		total = Arrays.asList(t0,t1,t2,t3,t4,t5,t6,t7,t8,t9);
		updateAllCounts(puzzle);
	}
	
	public void updateAllCounts(int[][] puzzle)
	{
		for (int i = 0; i < 10; i++) {
			updateTotalN(puzzle, i);
		}
	}
	
	public void updateTotalN(int[][] puzzle, int n)
	{
		int nTotal = 0;
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if(puzzle[y][x] == n) nTotal++;
			}
		}
		total.set(n, nTotal);
	}
	
	public void addAction(PuzzleAction action)
	{
		int totOld = total.get(action.getOldValue());
		int totNew = total.get(action.getNewValue());
		totOld--;
		totNew++;
		total.set(action.getOldValue(), totOld);
		total.set(action.getNewValue(), totNew);
	}
	
	public void addMistake()
	{
		mistakes++;
	}
	
	public boolean isFinished()
	{
		if(totalFilledSpaces == 81) return true;
		else return false;
	}
	
	public int getTotalFilledSpaces()
	{
		int totalFilled = 0;
		if(total.size() > 0)
		{
			for (int i = 1; i < 10; i++) {
				totalFilled += total.get(i);
			}
		}
		return totalFilled;
	}
	
	public int getTotalEmptySpaces()
	{
		return total.get(0);
	}
}
