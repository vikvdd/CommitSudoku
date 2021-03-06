package model.savesystem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import events.SaveObserver;
import model.SudokuPuzzle;
import util.Util;

public class SaveManager 
{
	public static final String saveDir = "";
	public static final String fileExtension = ".txt";
	
	public static void saveSudokuPuzzle(SudokuPuzzle puzzle, Boolean overwrite)
	{	
		if(puzzle.getName()==null) 
		{
			puzzle.setName(createRandomPuzzleName());
		}
		
		File file = new File(saveDir + puzzle.getName() + fileExtension);
		if(file.exists())
		{
			if(!overwrite)
			{
				file = generateUniqueFileName(puzzle.getName());
				String fileName = file.getName().replaceFirst("[.][^.]+$", "");
				puzzle.setName(fileName);
			}
		}
		
		SerializationHandler.SaveObject(puzzle, file, false);
		Util.println("uzzle: " + puzzle.getName());
		SudokuSave save = new SudokuSave(puzzle.getName(), puzzle.getDifficulty());
		PuzzleSaveList.getInstance().addPuzzle(save);
	}
	
	public static SudokuPuzzle loadSudokuPuzzle(String fileName)
	{
		return loadSudokuPuzzle(new File(saveDir + fileName+ ".txt"));
	}
	
	public static SudokuPuzzle loadSudokuPuzzle(File file)
	{
		SudokuPuzzle puzzle = (SudokuPuzzle)SerializationHandler.LoadSave(file);
		return puzzle;
	}
	
	public static File generateUniqueFileName(String fileName)
	{
		return generateUniqueFileName(new File(fileName+".txt"));
	}
	
	public static File generateUniqueFileName(File file)
	{
		
		String fileName = file.getName().replaceFirst("[.][^.]+$", "");
		String extName = file.getName().substring(file.getName().lastIndexOf(".")+1);
		String genName = fileName;
		int counter = 1;
		if(file.exists())
		{
			while(file.exists())
			{
				genName = fileName + counter + "." + extName;
				file = new File(genName);
				counter++;
			}
		}
		
		return new File(genName);
	}
	
	private static String createRandomPuzzleName()
	{
		Random random = new Random();
		String name = "Puzzle" + random.nextInt(10000) * random.nextInt(10000);
		return name;
	}
	
	
}
