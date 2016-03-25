import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


 public class IQTest {
	
	static int size = 4;
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	
	public static void main(String[] args) throws IOException {
		int [][] array = new int [4][4];
		String line = br.readLine() ;
		int x =0;
		while(x < 4) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					if(line.charAt(j) == '#') {
						array[i][j] = 1;
					}
					if(line.charAt(j) == '.') {
						array[i][j] = 0;
					}
			}
			x++;
			line = br.readLine();
			}
		}
		if(test(array)) {
			System.out.println("YES");
		}
		else 
			if(solveIQ(array)) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
	}
	public static boolean solveIQ(int [][] array) {
		boolean solved = false;
		String squares = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				squares = array[i][j]+ "" + array[i][j + 1] + ""+ array [i + 1][j]+ "" + array[i + 1][j + 1] + "" ;   		
				if(valid(squares)) {
					solved = true;
					break;
				}
			}
		}
		return solved;
	}
	
	public static boolean valid(String word) {
		int sumOfDots = 0;
		int sumOfHashes = 0;
		boolean valid = false;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == '0'){
				sumOfDots++;
			}
			if(word.charAt(i) == '1') {
				sumOfHashes++;
			}
		}
		if((sumOfHashes == 1) || (sumOfDots == 1)) {
			valid = true;
		}
		return valid;
	}
	
	public static boolean test(int [][] array) {
		boolean testDone = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(array[i][j] == array[i][j+1] && array[i][j+1] == array[i+1][j]
						&& array[i+1][j] == array[i+1][j+1]) {
					testDone = true;
				}
			}
		}
		return testDone;
	}
	
}
