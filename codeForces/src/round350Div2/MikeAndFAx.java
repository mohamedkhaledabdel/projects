package round350Div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.text.StyledEditorKit.BoldAction;

public class MikeAndFAx {

	public static String reverse(String s) {
		String reversed = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			reversed = reversed + s.charAt(i);
		}
		return reversed;
	}

	public static Boolean plaindrom(String s) {
		return s.equals(reverse(s));
	}

	public static Boolean prob(String s, int k) {
		int counter = 0;
		String copy = "";
		int index = 0;
		copy = s.substring(0);
		index = copy.substring(1).indexOf(copy.charAt(0));
		System.out.println(index);
		if (index == -1) {
			counter = 0;
		}
		else { 
			
			if (plaindrom(copy.substring(0, index + 2))) {
				counter++;
				prob(s.substring(index+1),k-1);
			} 
			else {
				prob(s.substring(index+1),k);
			}
		}	
		
		return k == counter;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		int k = Integer.parseInt(br.readLine());
		System.out.println(prob(s, k));
		//System.out.println(plaindrom("saddas"));
	}

}
