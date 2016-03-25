import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class Doublets {
	
	public static ArrayList<String> input() throws IOException{
		ArrayList<String> input = new ArrayList<String>();
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String dictionary = br.readLine();
        while(!dictionary.isEmpty()) {
        	input.add(dictionary);
        	dictionary = br.readLine();
        }
        
		return input;
	}
	
	public static ArrayList<String> words() throws IOException {
		ArrayList<String> words = new ArrayList<String>();
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String dictionary = br.readLine();
        words.add(dictionary);
        dictionary = br.readLine();
        words.add(dictionary);
		return words;
	}

	
	public static boolean compare(char c1, char c2) {
		boolean equal = false;
		if(c1 == c2) {
			equal = true;
		}
		return equal;
	}
	
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> input = new ArrayList<String>(); 
		input = input();
		ArrayList<String> words = new ArrayList<String>(); 
		words = words();
		Stack<String> result = new Stack<String>();
		result.push(words.get(0));
		int cmp = 0;
		// add here the cases of the dictionary and the words
		for (int i = 1; i < input.size() ; i++) {
			if(input.get(i).matches(words.get(1))) {
				result.push(input.get(i));
			}
			if(result.peek().length() == input.get(i).length()) {
				for (int j = 0; j < result.peek().length(); j++) {
					if(compare(result.peek().charAt(j), input.get(i).charAt(j)) == false) {
						cmp++;
					}
				}
					if(cmp == 1) {
						result.push(input.get(i));
						cmp = 0;	
					}
			}
		}	
		if(result.size() == 2) {
			System.out.println("No solution");
		}
		else {	
			while(!result.isEmpty()) {
				System.out.println(result.pop());
			}
		}
	}
}
