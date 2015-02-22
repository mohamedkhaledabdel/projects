public class ROB {
	int size, head, tail;
	ROBbuf[] buffer;

	public ROB(int size) {
		this.size = size;
		buffer = new ROBbuf[size];
	}

	public boolean canAdd() {
		return buffer[tail] == null;
	}

	public void putInstruction(int instNum, String type, int dest) {
		ROBbuf newRow = new ROBbuf(instNum, dest, 0, type, false);
		buffer[tail] = newRow;
		tail++;
		tail %= size;
	}

	public void writeOn(int instNum, int value) {
		int index = instNum;
		buffer[index].value = value;
		buffer[index].ready = true;
	}

	public int getValueBeforeCommit() {
		return buffer[head].value;
	}

	public void commit() {
		if (buffer[head] == null)
			return;
		if (!buffer[head].ready)
			return;
		buffer[head] = null;
		head++;
		head %= size;
	}
}