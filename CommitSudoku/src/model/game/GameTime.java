package model.game;

import java.util.ArrayList;
import java.util.List;

import events.EventObserver;
import util.Util;

public class GameTime {
	
	private static GameTime instance;
	
	private long startTime = 0;
	private long elapsedTime = 0;
	private boolean active = false;
	
	private List<EventObserver> observers = new ArrayList<EventObserver>();
	
	private GameTime() {}	
	
	public static GameTime getInstance()
	{
		if(instance==null) instance = new GameTime();
		return instance;
	}
	
	public void attach(EventObserver observer)
	{
		observers.add(observer);
	}
	
	public void start()
	{
		active = true;
		startTime = System.nanoTime();
		elapsedTime = System.nanoTime() - startTime;
	}
	
	public void refresh()
	{
		elapsedTime = System.nanoTime() - startTime;
		notifyObservers();
	}
	
	public void stop()
	{
		active = false;
	}
	
	public Long getTime()
	{
		return elapsedTime;
	}
	
	public Long getStartTime()
	{
		return startTime;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	private void notifyObservers()
	{
		for(EventObserver observer : observers)
		{
			observer.update();
		}
	}
}
