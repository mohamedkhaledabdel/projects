import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class everyThing {
	static ArrayList <Integer> numbers = new ArrayList<Integer>();
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	public static void main(String[] args) throws IOException {
		System.out.println("please enter number from 0 to 99");
		String number = "";
		boolean correct = true; 
		number = br.readLine();
		while(!number.isEmpty()) {
			if(number.length() < 3) {
				correct = true;
				numbers.add(Integer.parseInt(number));
				number = br.readLine();
			}
			 else {
				 correct = false;
				 numbers.clear();
				 break;
			 }
		 }

		if(correct == true) {
			for (int i = 0; i < numbers.size(); i++) {
				if(numbers.get(i) != 42) {
					System.out.println(numbers.get(i));
				}
				else {
					break;
				}
			}
		}	
	}
	
}
