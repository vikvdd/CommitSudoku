package model.game;

import java.util.ArrayList;
import java.util.List;
import events.TimeListener;
import util.Util;

public class TimerEvent {
	
	private List<TimeListener> listeners;
	
	private long startTime = 0L;
	private long elapsedTime = 0L;
	private boolean active = false;
	private long refreshRate = 1000L;
	
	public TimerEvent() {
		listeners = new ArrayList<TimeListener>();
	}
	
	public TimerEvent(long elapsedTimeMilli)
	{
		elapsedTime = elapsedTimeMilli;
	}
	
	public void addTimeListener(TimeListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeTimeListener(TimeListener listener)
	{
		listeners.remove(listener);
	}
	
	public void start()
	{
		active = true;
		if(elapsedTime != 0)
		{
			startTime = System.currentTimeMillis()-elapsedTime;
		}
		else startTime = System.currentTimeMillis();
			
		elapsedTime = System.currentTimeMillis() - startTime;
		notifyOnStart();
	}
	
	public void stop()
	{
		active = false;
		notifyOnStop();
	}
	
	public void reset()
	{
		elapsedTime = 0;
		startTime = 0;
		active = false;
	}
	
	public void setElapsedTimeMilli(long milliseconds)
	{
		elapsedTime = milliseconds;
	}
	
	public Long getTime()
	{
		elapsedTime = System.currentTimeMillis()-startTime;
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
	
	private void notifyOnStart()
	{
		Util.println(listeners.size());
		for(TimeListener l : listeners)
		{
			l.onStart();
		}
	}
	
	private void notifyOnStop()
	{
		for(TimeListener l : listeners)
		{
			l.onStop();
		}
	}
}
