
public class Merge {
	
	public static int merge(int x, int y) {
		int finalResult = 0;
		String finalResultString = "";
		String xString = x + "";
		String yString = y + "";
		int j = 0;
		int i = 0;
		while(i < xString.length()){
			while(j < yString.length()) {
				finalResultString = finalResultString + xString.charAt(i) + yString.charAt(j);
				if(i + 1 < xString.length()){
					i++;
				}
				else{
					finalResultString = finalResultString + yString.substring(j);
					break;
				}
				if(i + 1 < xString.length()){
					i++;
				}
				else{
					finalResultString = finalResultString + yString.substring(j);
					break;
				}

			}
		}
		return finalResult = Integer.parseInt(finalResultString);
	}
	
	public static void main(String[] args) {
		System.out.println("mohamed".substring(1,7));
	}
}
