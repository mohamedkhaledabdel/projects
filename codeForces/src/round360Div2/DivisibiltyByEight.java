package round360Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DivisibiltyByEight {

	public static String divisible(String input) {
		int res = 0;
		int j = 1;
		int i = 0;
		if (Integer.parseInt(input) % 8 == 0) {
			return "Yes \n" + input;
		} else {
			while (i < input.length()) {
				while (j < input.length()) {
					res = Integer.parseInt(input.replace(input.substring(i, j)
							+ "", ""));
					if (res % 8 == 0) {
						System.out.println("YES");
						return input.replace(input.substring(i, j) + "", "");
					} else {
						j++;
					}
					i++;
				}
			}
		}
		return "NO";
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(divisible(br.readLine()));
	}
}
