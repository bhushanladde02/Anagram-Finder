/*
Name : Bhushan Ladde
Purpose : Converting time into the miliseconds 
*/
public class Timer {
	public static Long getCurrentTimeInMiliS(){
		return System.currentTimeMillis() % 1000;
	}
}
