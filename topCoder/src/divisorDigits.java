
public class divisorDigits {

	public static int howMany(int number) {
		String numberText = number + "";
		int sum = 0;
		
		for (int i = 0; i < numberText.length(); i++) {
			if(Integer.parseInt(numberText.charAt(i) + "" )!= 0 && 
				number % Integer.parseInt(numberText.charAt(i) + "" )== 0) {
				sum++;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(howMany(12340));
		
	}

}
