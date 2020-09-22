package model.game;

public class GameTime {
	
	private static GameTime instance;
	
	private long startTime = 0;
	private long elapsedTime = 0;
	private boolean active = false;
	
	private GameTime() {}	
	
	public static GameTime getInstance()
	{
		if(instance==null) instance = new GameTime();
		return instance;
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
}
