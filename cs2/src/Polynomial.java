
public class Polynomial {
	int[] coff;
	
	public Polynomial() {
		coff = new int[0];
	}
	
	public Polynomial(int [] coff) {
		this.coff = new int[coff.length];
		for (int i = 0; i < this.coff.length; i++) {
			this.coff[i] = coff[i];
		}
	}
	
	public int degree() {
		return this.coff.length - 1;
	}
	
	public Polynomial add(Polynomial p) {
		int[] res = new int[p.coff.length];
		for (int i = 0; i < this.coff.length; i++) {
			res[i] = this.coff[i] + p.coff[i];
		}
		return new Polynomial(res);
	}
	
	public static Polynomial add(Polynomial p1, Polynomial p2) {
		int[] res = new int[p1.coff.length];
		for (int i = 0; i < p1.coff.length; i++) {
			res[i] = p1.coff[i] + p2.coff[i];
		}
		return new Polynomial(res);
	}
	
	public static void main(String[] args) {
		int[] a1 = {2,4,6};
		int[] a2 = {2,4,6};
		Polynomial p1 = new Polynomial(a1);
		System.out.println(p1);
		Polynomial p2 = new Polynomial(a2);
		Polynomial res = add(p1,p2);
		for (int i = 0; i < res.coff.length; i++) {
			System.out.println(res.coff[i]);
		}
	}
}
