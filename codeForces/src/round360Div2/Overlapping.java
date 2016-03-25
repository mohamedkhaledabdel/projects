package round360Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Overlapping {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = br.readLine();
		int iAB = input.indexOf("AB"), iBA = input.indexOf("BA", iAB + 2);
		if (iBA == -1) {
			iBA = input.indexOf("BA");
			iAB = input.indexOf("AB", iBA + 2);
		}
		if (iAB == -1 || iBA == -1)
			System.out.print("NO");
		else
			System.out.print("YES");

	}

}
