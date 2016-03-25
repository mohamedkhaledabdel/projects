import java.io.ObjectInputStream.GetField;


public class Politics {
	String countryName;
	int noOfCitizens;
	boolean isRoyal;
	String continent;
	int politicalState;
	
	public Politics(String countryName, int noOfCitizens, boolean isRoyal,
			String continent, int politicalState) {
		this.countryName = countryName;
		this.noOfCitizens = noOfCitizens;
		this.isRoyal = isRoyal;
		this.continent = continent;
		this.politicalState = politicalState;
	}
	
	public int getState() {
		return politicalState;
	}
	
	public boolean getRoyal() {
		return isRoyal;
	}
	
	public void setDefCon(int p) {
		this.politicalState = p;
	}
	
	public void increaseCitizen(int c) {
		this.noOfCitizens = this.noOfCitizens + c;
	}
	
	public String toString() {
		return  "The country name is " + this.countryName + "\n" + "The number of citizens is " + this.noOfCitizens
				+ "\n" + "The royality is " + this.isRoyal + "\n" + "The continent is " + this.continent +
				"\n" + "The political state is " + this.politicalState;
	}
	
	public static void main(String[] args) {
		Politics p = new Politics("Blaa", 10000, true, "Africa", 1);
		p.increaseCitizen(100000000);
		System.out.println(p.toString());
		
	}
	
	
}
