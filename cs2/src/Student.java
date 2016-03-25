public class Student
{
	String name;
	int age;
	char gender;
	int id;
	static int sum = 0;

	public Student(String name)
	{
		this.name =  name;
	}

	public Student(String name, int age, char gender, int id)
	{
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

	public void changeName(String name)
	{
		this.name = name;
	}
	

	public void changeAge(int newAge)
	{
		age = newAge;
	}

	public  void clone(Student s)
	{
		//s = this;  will work however the two references will be the same.
		this.name = s.name;
		this.age = s.age;
		this.id = s.id;
		this.gender = s.gender;
		System.out.println(this == s);


	}

	public boolean equals(Student s) {
		return this.name == s.name;
	}
	
	public static void equals(Student s1, Student s2) {
		System.out.println(s1.name == s2.name);
	}
	
	public String toString()
	{
		return name + " " + age + " " + gender;
	}


	public static void main(String[] args) {
		Student s1 = new Student("mohamed");
		s1.name = "hhahaha";
		System.out.println(s1.name);
		s1.setName("hassan");
		Student s2 = new Student("mohamed");
		System.out.println(s1.getName());
		equals(s1,s2);
		System.out.println(Integer.MAX_VALUE);
		int num = 9876551;
		String num2 = num + "";
	
		System.out.println(Integer.parseInt(num2)));
	}
}