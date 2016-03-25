import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class theTrip {
	static float avg = 0;
	static float sum = 0;
	static float diff = 0;
	static float big = 0;
	public static void main(String[] args) throws IOException {
		ArrayList<Float> numbers = new ArrayList<Float>();
		File file = new File("/home/mohamed/Desktop/numbers");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		float numOfPeople = 0;
		if(Float.parseFloat(line) > 1000) {
			System.out.println("the number of students in the trip must be less than 1000");
		}
		while(line != null) {
			if(Float.parseFloat(line) != 0 ) {
				numOfPeople = Float.parseFloat(line);
				for (float i = 0; i < numOfPeople; i++) {
					numbers.add(Float.parseFloat(br.readLine()));
				}
				System.out.println(exchange(numbers));
				line = br.readLine();				
			}
			else {
				break;
			}
		}	
			
	}
	
	public static String exchange(ArrayList<Float> number) {
		
		Object[] used = number.toArray();
		for (int i = 0; i < used.length; i++) {
			sum = sum + number.get(i);
		}
		avg = sum / number.size();
		for (int j = 0; j < used.length; j++) {
			if((float)used[j] <= avg) {
				used[j] = avg;
			}
			else {
				big = (float) used[j];
				diff = diff + (big - avg);
				used[j] = avg;
			}
		}
		Object temp = diff;
		avg = 0;
		sum = 0;
		diff = 0;
		big = 0;
		number.clear();
		return "$" + temp;
	}
}
