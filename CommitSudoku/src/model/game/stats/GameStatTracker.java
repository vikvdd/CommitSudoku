package model.game.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import events.GameStatListener;
import model.game.actions.PuzzleAction;
import util.Util;


public class GameStatTracker{

	private List<GameStatListener> listeners = new ArrayList<GameStatListener>();
	
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
	
	public void init(int[][] puzzle)
	{
		total = Arrays.asList(t0,t1,t2,t3,t4,t5,t6,t7,t8,t9);
		updateAllCounts(puzzle);
	}
	
	public void addGameStatListener(GameStatListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeGameStatListener(GameStatListener listener)
	{
		listeners.remove(listener);
	}
	
	public void updateAllCounts(int[][] puzzle)
	{
		for (int i = 0; i < 10; i++) {
			updateTotalN(puzzle, i);
			if(isNumberCompleted(i)) notifyNumberCompleted(i);
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
		if(isNumberCompleted(n)) notifyNumberCompleted(n);;
	}
	
	public void addAction(PuzzleAction action)
	{
		int oldVal = action.getOldValue();
		int newVal = action.getNewValue();
		int totOld = total.get(oldVal);
		int totNew = total.get(newVal);
		totOld--;
		totNew++;
		total.set(oldVal, totOld);
		total.set(newVal, totNew);
		
		
		isNumberCompleted(oldVal);
		isNumberCompleted(newVal);
	}
	
	public void addMistake()
	{
		mistakes++;
	}
	
	public boolean isNumberCompleted(int n)
	{
		if(total.get(n) >= 9)
		{
			notifyNumberCompleted(n);
			return true;
		}
		else {
			notifyNumberCompleted(n);
		}
		return false;
	}
	
	public boolean isPuzzleCompleted()
	{
		if(getTotalFilledSpaces() >= 81) return true;
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
		totalFilledSpaces = totalFilled;
		return totalFilledSpaces;
	}
	
	public int getTotalEmptySpaces()
	{
		totalEmptySpaces = total.get(0);
		return totalEmptySpaces;
	}
	
	private void notifyNumberCompleted(int number)
	{
		for (GameStatListener listener : listeners) {
			listener.onNumberCompleted(number);
		}
		if(isPuzzleCompleted()) notifyPuzzleCompleted();
	}
	
	private void notifyNumberUncompleted()
	{
		for(GameStatListener listener : listeners)
		{
			
		}
	}
	
	private void notifyPuzzleCompleted()
	{
		for(GameStatListener listener : listeners)
		{
			listener.onPuzzleCompleted();
		}
	}
}
