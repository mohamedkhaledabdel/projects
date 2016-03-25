
public class formatAmt {

	
	public static String amount(int dollars, int cents) {
		return "$" + addCents(dollar(dollars), cents);
		
	}
	
	public static String dollar(int dollars) {
		String amount = "";
		String dollarsString = dollars + "";
		if(dollarsString.length() <= 3 && dollarsString.length() > 1) {
			amount = dollarsString;
		}
		if(dollarsString.length() == 1) {
			amount =  dollarsString;
		}
		if(dollarsString.length() > 3) {
			amount = dollar(Integer.parseInt(dollarsString.substring(0, 
				dollarsString.length() - 3))) + "," + 
					dollarsString.substring(dollarsString.length() - 3, dollarsString.length()); 
		}
		return amount;
	}
	
	public static String addCents(String amount, int cents) {
		if(cents >= 0 && cents <= 9) {
			amount  = amount + "." + "0" + cents;
		}
		else {
			amount = amount + "." + cents;
		}
		return amount;
	}
	public static void main(String[] args) {
		System.out.println(amount(999999999, 10));
	}
}
