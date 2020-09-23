package tests;

public class Calculator {
	
	public static void main(String[] args)
	{
		loop();
	}
	
	//adds a + b
	public static int add(int a, int b)
	{
		int sum = a + b;
		return sum;
	}
	
	public static int subtract(int a, int b)
	{
		int difference = a - b;
		return difference;
	}
	
	public static boolean isEven(int number)
	{
		if((number/2) * 2 == number) return true;
		else return false;
	}
	
	public static void loop()
	{
		int number = 0;
		for(int i = 0; i <= 10; i++)
		{
			number = i;
			print(number + "");
		}
	}
	
	public static void print(String text)
	{
		System.out.println(text);
	}
	
}
