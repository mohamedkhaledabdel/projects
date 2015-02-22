
public class Cache {
	int S,L,C,m,cycle,hit,miss;
	boolean allocate,through;
	Line[] cache;
	int[] memory;
	
	public Cache(int S,int L,int m,int cycle,boolean allocate
			,boolean through,int[] memory) {
		this.S = S;
		this.L = L;
		this.m = m;
		this.allocate = allocate;
		this.through = through;
		this.memory = memory;
		this.cycle = cycle;
		this.C = S/L;
		cache = new Line[C];
		for(int i = 0; i < C; i++) {
			Line l = new Line(false, false,false, L/2);
			cache[i] = l;
		}
		hit = 0;
		miss = 0;
	}
	
	public boolean findInstruction(int pc,int current) {
		int sets = C/m;
		int redPc = pc / (L/2);
		int requiredSet = redPc % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(!l.occupied || l.holdsData) continue;
			for(int j = 0; j < l.length;j++ ) {
				Word w = l.words[j];
				if(w.address == pc) {
					l.lastmodified = current;
					hit++;
					return true;
					}
			}
		}
		miss++;
		return false;
	}
	
	public boolean findData(int index,int current) {
		int sets = C/m;
		int redInd = index / (L/2);
		int requiredSet = redInd % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(!l.occupied || !l.holdsData) continue;
			for(int j = 0; j < l.length;j++ ) {
				Word w = l.words[j];
				if(w.address == index) {
					l.lastmodified = current;
					hit++;
					return true;
					}
				}
		}
		miss++;
		return false;
	}
	
	public boolean readData(int index,int current) {
		int sets = C/m;
		int redIndex = index / (L/2);
		int requiredSet = redIndex % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(l.occupied) continue;
			for(int j = 0; j < l.length;j++ ) {
				int startindex = redIndex * (L / 2);
				l.words[j].address = startindex + j;
				l.words[j].value = memory[startindex + j];
			}
			l.lastmodified = current;
			l.occupied = true;
			l.holdsData = true;
			return true;
		}
		return false;
	}
	
	public int getData(int index,int current) {
		int sets = C/m;
		int redInd = index / (L/2);
		int requiredSet = redInd % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(!l.occupied || !l.holdsData) continue;
			for(int j = 0; j < l.length;j++ ) {
				Word w = l.words[j];
				if(w.address == index) {
					l.lastmodified = current;
					return w.value;
					}
				}
		}
		return -1;
	}
	
	public boolean addInstruction(int pc,int current) {
		int sets = C/m;
		int redPc = pc / (L/2);
		int requiredSet = redPc % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(l.occupied) continue;
			for(int j = 0; j < l.length;j++ ) {
				int startpc = redPc * (L / 2);
				l.words[j].address = startpc + j;
			}
			l.lastmodified = current;
			l.occupied = true;
			return true;
		}
		return false;
	}
	
	public void readAndReplaceData(int index,int current) {
		int sets = C/m;
		int redIndex = index / (L/2);
		int requiredSet = redIndex % sets;
		int startIndex = requiredSet * m;
		int lru = Integer.MAX_VALUE;
		int requiredLine = -1;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(!l.occupied) continue;
			if(l.lastmodified < lru) {
				lru = l.lastmodified;
				requiredLine = i;
			}
		}
		Line l = cache[requiredLine + startIndex];
		for(int j = 0; j < l.length;j++ ) {
			int startindex = redIndex * (L / 2);
			l.words[j].address = startindex + j;
			l.words[j].value = memory[startindex + j];
		}
		l.lastmodified = current;
		l.occupied = true;
		l.holdsData = true;
	}
	public void addAndReplaceInstruction(int pc,int current) {
		int sets = C/m;
		int redPc = pc / (L/2);
		int requiredSet = redPc % sets;
		int startIndex = requiredSet * m;
		int lru = Integer.MAX_VALUE;
		int requiredLine = -1;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(l.lastmodified < lru) {
				lru = l.lastmodified;
				requiredLine = i;
			}
		}
		Line l = cache[requiredLine + startIndex];
		for(int j = 0; j < l.length;j++ ) {
			int startpc = redPc * (L / 2);
			l.words[j].address = startpc + j;
		}
		l.lastmodified = current;
		l.occupied = true;
	}
	
	public boolean writeDataOnHit(int index,int value,int current) {
		int sets = C/m;
		int redIndex = index / (L/2);
		int requiredSet = redIndex % sets;
		int startIndex = requiredSet * m;
		boolean fit = false;
		int requiredIndex = -1;
			for(int i = 0; i < m; i++) {
				Line l = cache[i + startIndex];
				if(!l.occupied || !l.holdsData) continue;
				for(int j = 0; j < l.length;j++ ) {
					Word w = l.words[j];
					if(w.address == index) {
						requiredIndex = i;
						l.lastmodified = current;
						w.value = value;
						fit = true; 
						}
					}
			}
			if(fit) {
				if(through) {
					memory[index] = value;
				}
				else {
					Line l = cache[requiredIndex + startIndex];
					l.dirty = true;
				}
			}
		return fit;
	}
	
	public boolean writeDataOnMiss(int index,int value,int current) {
		if(!allocate) {
			memory[index] = value;
			return true;
		}
		int sets = C/m;
		int redIndex = index / (L/2);
		int requiredSet = redIndex % sets;
		int startIndex = requiredSet * m;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(l.occupied) continue;
			memory[index] = value;
			for(int j = 0; j < l.length;j++) {
				int startindex = redIndex * (L / 2);
				l.words[j].address = startindex + j;
				l.words[j].value = memory[startindex + j];
			}
			l.lastmodified = current;
			l.occupied = true;
			l.holdsData = true;
			return true;	
		}
		return false;
	}
	
	public void WriteDataAndReplace(int index,int value,int current) {
		memory[index] = value;
		int sets = C/m;
		int redIndex = index / (L/2);
		int requiredSet = redIndex % sets;
		int startIndex = requiredSet * m;
		int lru = Integer.MAX_VALUE;
		int requiredLine = -1;
		for(int i = 0; i < m; i++) {
			Line l = cache[i + startIndex];
			if(!l.occupied) continue;
			if(l.lastmodified < lru) {
				lru = l.lastmodified;
				requiredLine = i;
			}
		}
		Line l = cache[requiredLine + startIndex];
		if(l.dirty) {
			for(int j = 0; j < l.length;j++ ) {
				int address = l.words[j].address;
				memory[address] = l.words[j].value;
			}
			l.dirty = false;
		}
		for(int j = 0; j < l.length;j++ ) {
			int startindex = redIndex * (L / 2);
			l.words[j].address = startindex + j;
			l.words[j].value = memory[startindex + j];
		}
		l.lastmodified = current;
		l.occupied = true;
		l.holdsData = true;
		
	}
	
	
	

}
