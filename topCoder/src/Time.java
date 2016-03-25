import java.io.ObjectInputStream.GetField;


public class Time {

	public static String whatTime(int time) {
		String msg = "";
		if(time >=0 && time <= 86399) {
			msg = getHours(time) + ":" + getMinutes(time) + ":" + getSeconds(time);
		}
		else {
			msg = "invalid number of seconds";
		}
		return msg;
	}
	
	public static int getHours(int time) {
		return (int) time/3600;
	}
	
	public static int getMinutes(int time) {
		int hours = (int) getHours(time);
		int minutes = time -  (hours * 3600);
		return (int) minutes / 60;
	}
	
	public static int getSeconds(int time){
		int minutes = getMinutes(time);
		int hours = getHours(time);
		int seconds = time - (hours*3600) -  (minutes * 60);
		return seconds;
	}
	
	public static void main(String[] args) {
		System.out.println(whatTime(86399));
	}
}
