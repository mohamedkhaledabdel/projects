import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MultRec {
	
	public static int mult(int x, int y) {
		int result = 0;
		if(y == 0 ) {
			result = 0;
		}
	
		else{
			result = x + mult(x,y-1);
		}
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(mult(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
	}
}
