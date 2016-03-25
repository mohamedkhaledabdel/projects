import java.awt.image.SampleModel;

public class Person {
	public String name;
	public int age;

	public Person(String s, int n) {
		name = s;
		age = n;
	}

	public boolean sameNameAs(Person other) {
		return (name == other.name);
	}

	public boolean olderThan(Person other) {
		return (age > other.age);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public  void clone(Person s)
	{
		//s = this;  will work however the two references will be the same.
		this.name = s.name;
		this.age = s.age;
		


	}
	
	public static void main(String[] args) {
		Person p = new Person("mohamed", 22);
		Person p2 = new Person("mohd",23);
		p.clone(p2);
		System.out.println(p.name);
	}
}