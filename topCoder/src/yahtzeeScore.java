import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


public class yahtzeeScore {
	
	static ArrayList<Integer> index  = new ArrayList<Integer>();
	static ArrayList<Integer> tossToDo  = new ArrayList<Integer>();
	
	public static int maxPoints(int [] toss) {
		for (int i = 0; i < toss.length; i++) {
			tossToDo.add(toss[i]);
		}
		Collections.sort(tossToDo);
		int i = 0;
		int sum = 1;
		while(i < tossToDo.size() - 1){
			if(tossToDo.get(i) == tossToDo.get(i + 1)) {
				sum++;
				i++;
			}
			else{
				sum = 1;
				i++;
			}
			index.add(sum*tossToDo.get(i));
		}
		Collections.sort(index);
		return index.get(index.size() - 1);
	}
	
	public static void main(String[] args) {
		int [] test = new int [5];
		test[0] = 2;
		test[1] = 2;
		test[2] = 3;
		test[3] = 5;
		test[4] = 4;
		System.out.println(maxPoints(test));
	}
}
