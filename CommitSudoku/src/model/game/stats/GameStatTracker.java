package model.game.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStatTracker {
	int[][] puzzle = new int[9][9];
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
	
	public GameStatTracker(int[][] puzzle)
	{
		this.puzzle = puzzle;
		total = Arrays.asList(t0,t1,t2,t3,t4,t5,t6,t7,t8,t9);
	}
	
	public void init()
	{
		updateAllCounts();
	}
	
	public void updateAllCounts()
	{
		
	}
	
	public void updateTotalN(int n)
	{
		int tot = total.get(n);
		
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
