package util;

public class TriggerDelay {
	private long startTime;
	private long endTime;
	private long delayTime;
	
	public TriggerDelay(long delayTime)
	{
		this.delayTime = delayTime;
	}
	
	public void startDelay()
	{
		startTime = System.nanoTime();
		endTime = startTime + delayTime;
	}
	
	public boolean isFinished()
	{
		long time = System.nanoTime();
		if(time > endTime && startTime != 0)
		{
			startTime = 0;
			return true;
		}
		return false;
	}
}
