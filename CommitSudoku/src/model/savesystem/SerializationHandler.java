package model.savesystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.text.DateFormat.BooleanAttribute;

import model.game.puzzle.SudokuPuzzle;
import util.Util;

public class SerializationHandler 
{
	public static final String fileExtension = ".txt";
	
	public static void SaveObject(Object obj, String fileName, Boolean append)
	{
		SaveObject(obj, new File(fileName + fileExtension), append);
	}
	
	public static void SaveObject(Object obj, File file, Boolean append)
	{
		try {
			FileOutputStream fOut = new FileOutputStream(file, append);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(obj);
			out.flush();
			out.close();
			fOut.flush();
			fOut.close();
			Util.println("Saved " + file.getName() + " successfully!");
		} catch (Exception e) 
		{
			Util.println("ERROR: " + e);
		}
		
	}
	
	public static Object LoadSave(String fileName)
	{
		return LoadSave(new File(fileName + fileExtension));
	}
	
	public static Object LoadSave(File file)
	{
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			Object obj = in.readObject();
			in.close();
			Util.println("Loaded " + file.getName() + " successfully!");
			return obj;
		} catch (Exception e) {
			Util.println("ERROR: " + e);
		}
		return new Object();
	}	
}
