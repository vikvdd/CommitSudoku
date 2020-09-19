package model.game.actions;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.PasteAction;

import org.eclipse.ui.operations.UndoActionHandler;

import util.Util;

public class ActionLog {
	
	private static ActionLog instance;
	
	private List<PuzzleAction> actions = new ArrayList<PuzzleAction>();
	private int actionIndex = -1;
	
	public ActionLog()
	{
		actions = new ArrayList<PuzzleAction>();
	}
	
	public static ActionLog getInstance()
	{
		if(instance == null) instance = new ActionLog();
		return instance;
	}
	
	public PuzzleAction getAction()
	{
		return actions.get(actionIndex);
	}
	
	public void addAction(PuzzleAction action)
	{
		if(actionIndex > 0 && actionIndex < actions.size()-1) removeOldActions();
		actions.add(action);
		actionIndex = actions.size()-1;
	}
	
	public void undoAction()
	{
		if(actionIndex > 0) actionIndex--;
	}
	
	public void redoAction()
	{
		if(actionIndex < actions.size()-1) actionIndex++;
	}
	
	public void restartLog()
	{
		actions = new ArrayList<PuzzleAction>();
	}
	
	public void removeOldActions()
	{
		List<PuzzleAction> newActionList = new ArrayList<PuzzleAction>();
		Util.println("wtf");
		for(int i = 0; i < actionIndex+1; i++)
		{
			newActionList.add(actions.get(i));
		}
		actions = newActionList;
		Util.println(actions.size() + ">>>");
	}
}
