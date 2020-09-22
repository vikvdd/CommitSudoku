package events;

import java.util.ArrayList;
import java.util.List;

import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import util.Util;

public class GameModelEvent {
	
	private List<GameListener> listeners;
	
	public GameModelEvent()
	{
		listeners = new ArrayList<GameListener>();
	}
	
	public void addGameListener(GameListener listener)
	{
		listeners.add(listener);
	}
	public void removeGameListener(GameListener listener)
	{
		listeners.remove(listener);
	}
	protected void notifyGameStart(int[][] puzzleClone)
	{
		for(GameListener listener : listeners)
		{
			listener.onGameStart(puzzleClone);;
		}
	}
	protected void notifyGameEnd()
	{
		for(GameListener listener : listeners)
		{
			listener.onGameEnd();
		}
	}
	protected void notifyPuzzleChanged(String name, Difficulty difficulty, String elapsedTime)
	{
		for(GameListener listener : listeners)
		{
			listener.onPuzzleChanged(name, difficulty, elapsedTime);
		}
	}
	protected void notifyNumberEntry(Coordinate coord, int num)
	{
		for(GameListener listener : listeners)
		{
			listener.onNumberEntry(coord, num);
		}
	}
	protected void notifyPuzzleCompleted()
	{
		for(GameListener listener : listeners)
		{
			listener.onPuzzleCompleted();
		}
	}
}
