
public class Line {
	boolean holdsData,dirty,occupied;
	int length,lastmodified;
	Word[] words;
	public Line(boolean holdsData,boolean dirty,boolean occupied,int length) {
		this.holdsData = holdsData;
		this.dirty = dirty;
		this.length = length;
		this.occupied = occupied;
		words = new Word[length];
		for(int i = 0; i < length; i++) {
			Word w = new Word(0, 0);
			words[i] = w;
		}
	}

}
