public class Timer {
	public static Long getCurrentTimeInMiliS(){
		return System.currentTimeMillis() % 1000;
	}
}
