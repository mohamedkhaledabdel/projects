public class ROBbuf {
	int instNum, dest, value;
	String type;
	boolean ready;

	public ROBbuf(int instNum, int dest, int value, String type, boolean ready) {
		this.instNum = instNum;
		this.dest = dest;
		this.value = value;
		this.type = type;
		this.ready = ready;
	}
}
