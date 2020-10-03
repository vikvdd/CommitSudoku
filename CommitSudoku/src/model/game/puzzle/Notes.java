package model.game.puzzle;

import java.io.Serializable;

import util.Util;

public class Notes implements Serializable{
	boolean[] status;
	
	public Notes()
	{
		status = new boolean[9];
	}
	
	public void setNoteStatus(int num, boolean isActive)
	{
		int i = num-1;
		try {
			status[i] = isActive;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void toggleNoteStatus(int num)
	{
		int i = num-1;
		try {
			boolean isActive = status[i];
			status[i] = !isActive;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean isNoteActive(int num)
	{
		int i = num-1;
		try {
			return status[i];
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
