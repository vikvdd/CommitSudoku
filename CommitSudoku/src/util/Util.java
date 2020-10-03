package util;

import java.util.Arrays;

public class Util {
	public static int[][] clone2dArray(int[][] array)
	{
		int[][] clone = new int[9][9];
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				clone[y][x] = array[y][x];
			}
		}
		return clone;
	}
	
	public static void println(String string)
	{
		System.out.println(string);
	}
	
	public static void println(int intText)
	{
		println(intText + "");
	}
	
	public static void print(String string)
	{
		System.out.print(string);
	}
	
	public static void print(int intText) {
		print(intText + "");
	}
	
	public static String formatStringToTitle(String text)
	{
		String[] words = text.split(" ");
		for(int i = 0; i < words.length; i++)
		{
			words[i] = capitalizeFirstLetter(words[i]);
		}
		return Arrays.toString(words);
	}
	
	public static String capitalizeFirstLetter(String word)
	{
		word = word.toLowerCase();
		String output = word.substring(0,1).toUpperCase() + word.substring(1);
		return output;
	}
}
