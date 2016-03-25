import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Power {
	
	public static int power(int m, int n) {
		return m^n;
	}
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int base = Integer.parseInt(br.readLine());
		int power = Integer.parseInt(br.readLine());
		System.out.println(power(base, power));
	}
	
}
