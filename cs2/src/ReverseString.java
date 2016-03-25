
public class ReverseString {

	public static int[] revRec(int[] a) {
		if(a.length == 0) {
			return a;
		}
		else{
			int temp = a[0];
			a[0] = a[a.length-1];
			a[a.length-1] = a[0];
		}
		
	}
	
}
