
public class binaryCode {
	public static String[] decode(String msg){
		String [] binary = new String[2];
		String none = "NONE";
		String [] decoded = new String[msg.length()];
		if(msg.charAt(0) == '3' || msg.charAt(msg.length())== '3') {
			binary [0] = none + ", ";
			binary [1] =  none;
		} else {
		for (int i = 0; i < msg.length(); i++) {
			if(msg.charAt(i) == 1 && (i == msg.length() - 1 || i== 0)) {
				if(msg.charAt(i + 1) == 1 ) {
					decoded[i] = "1"; 
					decoded[i + 1] = "0";
				}
				if(msg.charAt(i + 1) == 2) {
					decoded[i] = "0"; 
					decoded[i + 1] = "1";
				}
			}
			if(msg.charAt(i) == 2 && (i == msg.length() - 1 || i== 0)) {
				decoded[i] = "1"; 
				decoded[i + 1] = "1";
			}
			if(msg.charAt(i) == '3' && decoded[i - 1] != "0") {
				decoded[i - 1] = "1";
				decoded[i] = "1";
				decoded[i + 1] = "1";
			}
			else {
				binary [0] = none + ", ";
				binary [1] =  none;
				break;
			}
			if(msg.charAt(i) == 1) {
				if(decoded[i - 1] == "0") {
					decoded[i] = "1";
					decoded[i + 1] = "0";
				}
			}
		}
		}
		return decoded;
	}
	
	public static void main(String[] args) {
		
	}
	
}
