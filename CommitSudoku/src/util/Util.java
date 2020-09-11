package util;

public class Util {
	public static int[][] Clone2dArray(int[][] array)
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
	
	public static void print(String string)
	{
		System.out.print(string);
	}
}
