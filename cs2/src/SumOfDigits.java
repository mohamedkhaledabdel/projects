
public class SumOfDigits {

	public static int sum(String in) {
		int sum = 0;
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == '0' || in.charAt(i) == '1' || in.charAt(i) == '2' ||
					in.charAt(i) == '3' || in.charAt(i) == '4' || in.charAt(i) == '5' ||
					in.charAt(i) == '6' || in.charAt(i) == '7' || in.charAt(i) == '8' ||
					in.charAt(i) == '9') {
				sum = sum + Integer.parseInt(in.charAt(i)+"");
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(sum("The year has 12 months,each month has 4 weeks and the week has 7 days."));
	}
}
