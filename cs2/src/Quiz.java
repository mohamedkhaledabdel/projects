import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Quiz {
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String operation = br.readLine();
		String result = "";
		System.out.println(operation);
		if(operation == "cairo") 
			result = "150";
		else if(operation == "jajaja")
				result = "50";
		else if(operation == "alex")
				result = "50";
		else{
			result = "invalid";
		}
		System.out.println(result);
			if("cairo" == "cairo") {
				System.out.println("yess");
			}
			String a = "fos";
			String b = "fos";
			if(a == b) 
				System.out.println("yaaaaaa");
	}
}
