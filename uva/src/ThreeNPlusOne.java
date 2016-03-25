
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


 class ThreeNPlusOne {
	static ArrayList <Integer> list = new ArrayList<Integer>();
	static int size = 0;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int max = 0;
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
		String numbers = br.readLine();
        int first = Integer.parseInt(numbers.split(" ")[0]);//sc.nextInt();
		int last = Integer.parseInt(numbers.split(" ")[1]);//sc.nextInt();
		while(numbers != null) {
		if ((first > 0 &&  first < 1000000) && (last > 0 && last < 1000000)) {
			ArrayList <Integer> test = new ArrayList<Integer>();
			for (int i = first; i <= last; i++) {
				test.add(i);
			}
			int i = 0;
			while (i < test.size()) {
				if (cycleLength(test.get(i)) > max) {
					max = cycleLength(test.get(i)); 
					i++;
				}
				else {
					i++;
				}
			}
			System.out.println(first + " " + last + " " + max);
		}
		else {
			System.out.println("please enter numbers that are greater than zero and less than 1 million");
		}
		}
	}
	
	public static int cycleLength(int num) {
		if(num > 0 && num < 1000000) {
			list.add(num);
			if(num != 1) {
				if(num %2 == 0) {
					cycleLength(num/2);
				}
				else {
					cycleLength((num * 3) + 1);
				}
			}
			else {
				size = list.size();
				list.clear();
			}
		}
		else {
			System.out.println("the number is not greater than 0 or greater than 1 million");
		}
		return size;
	}
}
