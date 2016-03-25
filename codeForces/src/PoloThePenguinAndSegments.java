import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PoloThePenguinAndSegments {
	
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(isr);
	static String line = "";
	static int i = 0, k = 0;
	static int firstNumber = 0, secondNumber = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		line = br.readLine();
		i = Integer.parseInt(line.split(" ")[0]);
		k = Integer.parseInt(line.split(" ")[1]);
		line = br.readLine();
		firstNumber = Integer.parseInt(line.split(" ")[0]);
		secondNumber = Integer.parseInt(line.split(" ")[1]);
		System.out.println(moves(firstNumber, secondNumber));
		
	}
	
	public static int moves(int firstNumber, int secondNumber) throws NumberFormatException, IOException {
		int move = 0;
		int  j = 0;
		
			if(firstNumber % k == 0 || secondNumber % k == 0 || (secondNumber - firstNumber) % k == 0 ){
				move++;
				line = br.readLine();
				firstNumber = Integer.parseInt(line.split(" ")[0]);
				secondNumber = Integer.parseInt(line.split(" ")[1]);
				j++;
			}
			else {
				if(firstNumber < k && secondNumber < 1000) {
					moves(firstNumber, secondNumber++);
				}
				else if(firstNumber >= k && firstNumber > 0){
					moves(firstNumber--, secondNumber);
				}
			}
		
		return move;
	}
	
	
}
