public class Clerk {

	String firstName;
	String lastName;
	int yearOfBirth;
	int salary;
	boolean isMarried;
	double basicSalary = 2000;

	public Clerk(String firstName, String lastName, int yearOfBirth,
			int salary, boolean isMarried) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.salary = salary;
		this.isMarried = isMarried;
	}

	public void promote(int newSalaryClass) {
		this.salary = newSalaryClass;
	}

	public void marry() {
		this.isMarried = true;
	}

	public void divorce() {
		this.isMarried = false;
	}

	public double salary(int year) {
		double newSalary = this.basicSalary + ((17 / 19) * this.salary) / 100;
		int age = year - this.yearOfBirth;
		int nbonus = 0 ;
		if(age >= 30)
		nbonus = (age - 25) / 5 ;
		newSalary = ((100.0 + nbonus) / 100.0) * newSalary;
		if(this.isMarried)
			newSalary += (12.3 / 100.0) * this.basicSalary;
		return newSalary ;
	}
	
	public static void main(String[] args) {
		System.out.println((int) (6*Math.random()));
	}
}
