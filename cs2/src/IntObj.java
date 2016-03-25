
public class IntObj {
	int v;
	
	public void setInt(int v) {
		this.v = v;
	}
	
	public int getInt() {
		return v;
	}
	
	public static void swap(IntObj o1, IntObj o2) {
		IntObj o3 = o1;
		o1 = o2;
		o2 = o3;
	}
	
	public static void main(String[] args) {
		
	}
	
}
