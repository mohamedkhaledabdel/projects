public class ReservationStationRow {
	public static String name;
	public static boolean busy;
	public static String op;
	public static int vj;
	public static int vk;
	public static int qj;
	public static int qk;
	public static int destination;
	public static int address;
	

	public ReservationStationRow(String name, boolean busy, String op,
			int vj, int vk, int qj, int qk, int destination, int address) {
	this.name = name;
	this.busy =busy;
	this.op =op;
	this.vj = vj;
	this.vk = vk;
	this.qj = qj;
	this.qk = qk;
	this.destination = destination;
	this.address = address;
}
		

	
}
