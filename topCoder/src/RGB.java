import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class RGB {
	static int maxSum = 0;
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	static int sum = 0;
	static int index = 0;
	
	public static int returnMaxSum(ArrayList<String> input) {
		for (int i = 0; i < input.size(); i++) {
			sum = sumInString(input.get(i));
			if(sum > maxSum) {
				maxSum = sum;
				index = i;
			}
		}
		return index;
	}
	
	public static int sumInString(String s) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum = sum + Integer.parseInt(s.split(" ")[i]);
		}
		return sum;
	}
	
	public static String pickSmallestFromString(String s) {
		int smallest = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < 3; i++) {
			if(Integer.parseInt(s.split(" ")[i]) < smallest ) {
				smallest = Integer.parseInt(s.split(" ")[i]);
				index = i;
			}
		}
		return smallest + "-" + index;
	}
	
	
	
	public static int allTogether(ArrayList<String> input) {
		ArrayList<String> allSumsAndIndex = new ArrayList<String>();
		int index = 0;
		index = returnMaxSum(input);
		allSumsAndIndex.add(pickSmallestFromString(input.get(index)));
		input.remove(index);
		for (int i = 0; i < input.size(); i++) {
			if(allSumsAndIndex.get(allSumsAndIndex.size() - 1).split("-")[1] == 
					pickSmallestFromString(input.get(index)).split("-")[1]) {
				removeSmallest(input.get(i));
			}
		}
	}
	
	public static String removeSmallest(String s) {
		String smallestToBeRemoved = pickSmallestFromString(s).split("-")[0];
		return s.replace(smallestToBeRemoved, "");
	}
	
	public static String getAnotherSmallest(String s) {
		int smallest = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			if(Integer.parseInt(s.split(" ")[i]) < smallest ) {
				smallest = Integer.parseInt(s.split(" ")[i]);
				index = i;
			}
		}
		return smallest + "-" + index;
	}
	
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		test.add("900 1 200");
		test.add("1 600 200");
		test.add("1 800 100");
		System.out.println(test);
		System.out.println(returnMaxSum(test));
		System.out.println(pickSmallestFromString(test.get(0)));
		System.out.println(removeSmallest(test.get(0)));
	}
}
