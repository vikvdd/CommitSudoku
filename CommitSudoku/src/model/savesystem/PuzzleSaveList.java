package model.savesystem;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PuzzleSaveList implements Serializable
{
	public static PuzzleSaveList instance;
	
	private static final long serialVersionUID = -3790626449400083854L;
	private static final String SAVE_DIR = "gamesaves/";
	private static final String FILE_NAME = "PuzzleList";
	private List<SudokuSave> saveList;
	private SudokuSave selectedSave;
	
	private PuzzleSaveList() {
		saveList = new ArrayList<SudokuSave>();
		loadSaveList();	
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
			Util.println(SAVE_DIR + FILE_NAME + ".txt");
			File file = new File(SAVE_DIR + FILE_NAME + ".txt");
			if(!file.exists())
			{
				file.createNewFile();
			}
			else 
			{
				saveList = new ArrayList<SudokuSave>();
				try {
					PuzzleSaveList puzzleSaveList = (PuzzleSaveList)SerializationHandler.LoadSave(file);
					setSaveList(puzzleSaveList.getSaveList());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		catch (Exception e) {
			Util.print("ERROR: " + e);
		}
		return;
	}
	
	private static void updateSaveFile()
	{
		SerializationHandler.SaveObject(instance, new File(SAVE_DIR + FILE_NAME + ".txt"), false);			
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
