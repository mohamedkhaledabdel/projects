
public class SplitArray {

	public static void split(int [] array, int pivot, int size) {
		int [] result = new int [size];
		for (int i = 0; i < array.length; i++) {
			if(array[i] < pivot) {
				result[i]  = array[i];
			}
			else {
				result[size - i - 1] = array[i];
			}
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	public static void main(String[] args) {
		int [] array1 = {12, 32, 14, 35, 89, 16, 120};
		split(array1, 14, 7);
	}
}
