import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Activity {

	public static String activity(String day) {
		String whatToDo = "";
		switch (day) {
		case "Monday":
		case "Thursday":
			whatToDo = ("Train Your Upper Boday");
			break;
		case "Tuesday":
		case "Friday":
			whatToDo = ("Train Your Lower Boday");
			break;
		case "Wednesday":
		case "Saturday":
		case "Sunday":
			whatToDo = ("Take off");
			break;
		default:
			whatToDo = "Please Enter A valid day";
			break;
		}
		return whatToDo;
	}
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(activity(br.readLine()));
	}

}
