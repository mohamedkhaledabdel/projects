import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Calculator {
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		double op1 = Double.parseDouble(br.readLine());
		double op2 = Double.parseDouble(br.readLine());
		String operation = br.readLine();
		double result = 0;
		switch (operation) {
			case "+":
				result = op1 + op2;
			break;
			case "-":
				result = op2 - op1;
			break;
			case "*":
				result = op1 * op2;
			break;
			case "/":
				result = op1 / op2;
			break;

		default: System.out.println("enter a valid operation");
			break;
		}
		System.out.println(result);
	}
}
