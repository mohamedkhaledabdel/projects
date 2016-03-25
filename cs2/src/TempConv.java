import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TempConv {
	static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    
    public static double convertToFahr(double deg) {
    	return ((9.0/5 )* deg) + 32;
    }
    
    public static double convertToKelvin(double deg) {
    	return deg + 273;
    }
    
    public static void main(String[] args) throws IOException {
		System.out.println("please enter the degreee in celisus");
		String line = br.readLine();
		System.out.println("The degree in Fahr:- " + convertToFahr(Double.parseDouble(line)));
		System.out.println("The degree in Kelvin:- " + convertToKelvin(Double.parseDouble(line)));
	}
	
}
