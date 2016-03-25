
public class UnionAndIntersection {
	static String b;
	
	
	public static  void intersect(int [] a, int [] b) {
		String intersect = "";
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if(b[j] == a[i]) {
					intersect = intersect + b[j] + " " ;
				}
			}
		}
		System.out.println(intersect);
	} 
	
	public static void union(int [] a, int [] b) {
		String union = "";
		for (int i = 0; i < a.length; i++) {
			union = union + a[i] + " " ;
		}
		for (int i = 0; i < b.length; i++) {
			if(!union.contains(b[i]+"")) {
				union = union + b[i] + " ";
			}
		}
		System.out.println(union);
	}

	public static void main(String[] args) {
		int [] array1 = {12, 32, 14, 35, 89, 16, 120};
		int [] array2 = {9, 12, 8, 17, 120, 35, 36};
		intersect(array1, array2);
		union(array1,array2);
	}
	
	
}
