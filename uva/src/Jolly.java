import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.print.DocFlavor.INPUT_STREAM;


public class Jolly {
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		File file = new File("/home/mohamed/Desktop/jolly");
		BufferedReader br = new BufferedReader(new FileReader(file));	
		String num = br.readLine();
		String [] arrNum = new String[num.split(" ").length] ;
		while(num != null) {
			arrNum = num.split(" ");
			System.out.println(jolly(arrNum));
			num = br.readLine();
			
		}	
	}
	
	public static String jolly(String [] org) {
		String jolly = "jolly";
		Stack<Integer> minus= new Stack<Integer>();
		minus.push(Integer.parseInt(org[0])- Integer.parseInt(org[1]));
		for (int i = 1; i < org.length - 1 ; i++) {
			if((Math.sqrt(Math.pow(Integer.parseInt(org[i])- Integer.parseInt(org[i+1]), 2))) <= minus.peek()) {
				minus.push((int) Math.sqrt(Math.pow(Integer.parseInt(org[i])- Integer.parseInt(org[i+1]), 2)));

			}
			else {
				jolly = "not jolly";
				break;
			}
		}
		return jolly;
	}
}
