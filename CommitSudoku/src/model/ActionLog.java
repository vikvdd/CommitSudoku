package model;

import java.util.ArrayList;
import java.util.List;

public class ActionLog {
	
	private static ActionLog instance;
	
	private List<PuzzleAction> actions = new ArrayList<PuzzleAction>();
	private int lastAction = -1;
	
	private ActionLog()
	{
		
	}
	
	public static ActionLog getInstance()
	{
		if(instance == null) instance = new ActionLog();
		return instance;
	}
	
	
	public void performAction(PuzzleAction action)
	{
		if(lastAction < actions.size()-1)
		{
			removeActionsFromEnd(lastAction+1);
		}
		lastAction++;
		actions.add(action);
	}
	
	public PuzzleAction getPreviousAction()
	{
		if(lastAction != 0) lastAction--;
		return actions.get(lastAction);
	}
	
	public PuzzleAction getNextAction()
	{
		if(lastAction < actions.size()-1)
		{
			lastAction++;
		}
		return actions.get(lastAction);
	}
	
	private void removeActionsFromEnd(int startIndex)
	{
		for(int i = startIndex; startIndex < actions.size(); startIndex++)
		{
			actions.remove(i);
		}
	}

}
