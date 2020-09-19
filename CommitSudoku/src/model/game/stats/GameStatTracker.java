package model.game.stats;

import java.util.ArrayList;
import java.util.List;

public class GameStatTracker {
	int[][] puzzle = new int[9][9];
	List<Integer> total = new ArrayList<Integer>();
	int t1;
	int t2;
	int t3;
	int t4;
	int t5;
	int t6;
	int t7;
	int t8;
	int t9;
	
	public GameStatTracker(int[][] puzzle)
	{
		this.puzzle = puzzle;
	}
	
	public void init()
	{
		updateAllCounts();
	}
	
	public void updateAllCounts()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if(puzzle[y][x] != 0)
				{
					int tN = total.get(puzzle[y][x]-1);
					tN++;
				}	
			}
		}
	}
	
	public void updateTotalN(int n)
	{
		if(n != 0)
		{
			int count = 0;
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					if(puzzle[y][x] == n) count++;
				}
			}
			int tot = total.get(n-1);
			tot = count;
		}
	}
	
	public boolean isFinished()
	{
		updateAllCounts();
		int count = 0;
		for(int n : total)
		{
			count+=n;
		}
		if(count == 81) return true;
		else return false;
	}
}
