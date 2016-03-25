
public class AirPlane {

	String name;
	double emptyWeight;
	int numberOfSeats;
	double fuelConsumption;
	static int sum = 0;
	
	public AirPlane(String name, double emptyWeight, int numberOfSeats, double fuelConsumption) {
		this.name = name;
		this.emptyWeight = emptyWeight;
		this.numberOfSeats = numberOfSeats;
		this.fuelConsumption = fuelConsumption;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static void addSum() {
		sum++;
	}
	
	public double getEmptyWeight() {
		return this.emptyWeight;
	}
	
	public int getSeats() {
		return this.numberOfSeats;
	}
	
	public double getFuelConsumption() {
		return this.getFuelConsumption();
	}
	
	public void addSeats(int seats) {
		this.numberOfSeats = this.numberOfSeats + seats;
	}
	
	public static void main(String[] args) {
		AirPlane ap = new AirPlane("s1", 1.0, 200, 1.0);
		ap.addSeats(50);
		System.out.println(ap.getSeats());
		addSum();
		addSum();
		System.out.println(sum);
	}
	
}
