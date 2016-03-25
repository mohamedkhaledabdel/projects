package srm665Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LuckyNumber {
	
	public static int lucky(int x) {
		int num = -1;
		for(int i = x + 1; i <= 100; i++) {
			if(holdsFour((i ^ x) + "") || holdsSeven((i ^ x) + "")) {
				num = i;
				break;
			}
			else {
				num = -1;
			}
		}
		return num;
	}
	
	public static boolean holdsFour(String x) {
		boolean holdsFour = false;
		for(int i = 0; i < x.length(); i++) {
			if(x.charAt(i) == '4') {
				holdsFour = true;
			}
			else {
				holdsFour = false;
				break;
			}
		}
		return holdsFour;
	}
	
	public static boolean holdsSeven(String x) {
		boolean holdsSeven = false;
		for(int i = 0; i < x.length(); i++) {
			if(x.charAt(i) == '7') {
				holdsSeven = true;
			}
			else {
				holdsSeven = false;
				break;
			}
		}
		return holdsSeven;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int num = Integer.parseInt(br.readLine());
		if(num >= 1 && num <= 100) {
			System.out.println(lucky(num));
		}
		else {
			System.out.println("please enter a number between 1 and 100");
		}
	}
}
