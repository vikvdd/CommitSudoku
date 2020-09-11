package model.savesystem;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import events.SaveObserver;
import model.Difficulty;

import util.Util;

public class PuzzleSaveList implements Serializable
{
	public static PuzzleSaveList instance;
	
	private static final long serialVersionUID = -3790626449400083854L;
	private static final String FILE_NAME = "PuzzleList";
	private List<SudokuSave> saveList;
	private SudokuSave selectedSave;
	
	private PuzzleSaveList() {
		saveList = new ArrayList<SudokuSave>();
		loadSaveList();	
		Util.println(saveList.size() + "------");
	}
	
	public static PuzzleSaveList getInstance()
	{
		if(instance == null) 
		{
			instance = new PuzzleSaveList();
		}
		return instance;
	}
	
	public void refresh()
	{
		loadSaveList();
	}
	
	private void loadSaveList()
	{
		try
		{
			File file = new File(FILE_NAME + ".txt");
			if(!file.exists())
			{
				file.createNewFile();
			}
			else 
			{
				saveList = new ArrayList<SudokuSave>();
			}
			PuzzleSaveList puzzleSaveList = (PuzzleSaveList)SerializationHandler.LoadSave(file);
			setSaveList(puzzleSaveList.getSaveList());
		}
		catch (Exception e) {
			Util.print("ERROR: " + e);
		}
		return;
	}
	
	private static void updateSaveFile()
	{
		SerializationHandler.SaveObject(instance, FILE_NAME, false);			
	}
	
	public void setSaveList(List<SudokuSave> list)
	{
		saveList = list;
	}
	
	public void setSelectedSave(SudokuSave save)
	{
		selectedSave = save;
	}
	
	public List<SudokuSave> getSaveList()
	{
		return saveList;
	}
	
	public SudokuSave getSelectedSave()
	{
		return selectedSave;
	}
	
	public void addPuzzle(SudokuSave save)
	{
		for(SudokuSave item : saveList)
		{
			if(save.getName().toLowerCase().equals(item.getName().toLowerCase()))
			{
				return;
			}
		}
		saveList.add(save);
		updateSaveFile();
		loadSaveList();
	}
	
	public String[] getListAsStrings() 
	{
		String[] stringList = new String[saveList.size()];
		for (int i = 0; i < stringList.length; i++) 
		{
			stringList[i] = saveList.get(i).getName();
		}
		return stringList;
	}
	
	private static String FormatString(SudokuSave save)
	{
		String str = save.getName() + " - " + save.getDifficulty().toString();
		return str;
	}

}
