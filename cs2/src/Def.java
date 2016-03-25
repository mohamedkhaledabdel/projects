
public class Def {
	Person[] test;
	
	public Def(Person[] t) {
		test = new Person[t.length];
		for (int i = 0; i < t.length; i++) {
			this.test[i] = t[i];
		}
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("mohamed", 22);
		Person p2 = new Person("ahmed", 20);
		Person[] l1 = {p1,p2};
		Def d1 = new Def(l1);
		Person[] l2 = {p2,p1};
		d1 = new Def(l2);
		System.out.println(d1.test[1].name);
	}
}
