package tests;

import java.sql.Time;

import events.TimeListener;
import model.game.TimerEvent;
import util.Util;

public class TimerTest implements TimeListener{
	
	

	public static void main(String[] args) {
		TimerEvent timerEvent = new TimerEvent();
		TimerTest test = new TimerTest();
		timerEvent.addTimeListener(test);
		timerEvent.start();
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Util.println("Started timer");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}
	
}
