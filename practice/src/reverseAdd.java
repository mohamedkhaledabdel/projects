import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class reverseAdd {

	public static int addReverse(String operand1, String operand2) {
		Stack <Integer> firstOperand = new Stack<Integer>();
		Stack <Integer> secondOperand = new Stack<Integer>();
		Stack <Integer> resultReversed = new Stack<Integer>();
		int result = 0;
		for (int i = 0; i < operand1.length(); i++) {
			firstOperand.push(Integer.parseInt("" + operand1.charAt(i)));
		}		
		for (int i = 0; i < operand2.length(); i++) {
			secondOperand.push(Integer.parseInt("" + operand2.charAt(i)));
		}
		operand1 = "";
		operand2 = "";
		while(!firstOperand.isEmpty()) {
			operand1 = operand1 + firstOperand.pop();
		}
		while(!secondOperand.isEmpty()) {
			operand2 = operand2 + secondOperand.pop();
		}
		result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
		String resultString = "" + result;
		for (int i = 0; i < resultString.length(); i++) {
			resultReversed.push(Integer.parseInt("" + resultString.charAt(i)));
		}
		resultString = "";
		while(!resultReversed.isEmpty()) {
			resultString = resultString + resultReversed.pop();
		}
		result = Integer.parseInt(resultString);
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int counter = Integer.parseInt(br.readLine());
		String line = "";
		int i = 0;
		line = br.readLine();
		while(i < counter && counter <= 10000) {	
			System.out.println(addReverse(line.split(" ")[0], line.split(" ")[1]));
			line = br.readLine();
			i++;
		}
	}
	
}
