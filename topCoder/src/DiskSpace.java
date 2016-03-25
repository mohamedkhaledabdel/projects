import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class DiskSpace {

	public static int minDrives(int [] used, int [] total ) {
		ArrayList<Integer> free = new ArrayList<Integer>();
		ArrayList<Integer> usedList = new ArrayList<Integer>();
		for (int i = 0; i < used.length; i++) {
			usedList.add(used[i]);
		}
		for (int i = 0; i < total.length; i++) {
			free.add(total[i] - usedList.get(i));
		}
		Collections.sort(usedList);
		for (int i = 0; i < free.size(); i++) {
			for (int j = 0; j < total.length; j++) {			
				if(usedList.get(i) > 0) {
					usedList.set(i, usedList.get(i) - free.get(j)); 
					if(usedList.get(i) > free.get(j))
						free.set(j, 0);
					else
						free.set(j, usedList.get(i) - free.get(j) );
				}
			}
		}
		int sumOfDrives = 0;
		for (int i = 0; i < usedList.size(); i++) {
			if(usedList.get(i) < 0) {
				sumOfDrives++;
			}
		}
		return sumOfDrives;
	}
	
	public static void main(String[] args) {
		int [] usedList = {750,800,850,900,950};
		int [] total  = {1000,200,200,200,200,200};
		
		System.out.println(minDrives(usedList, total));
	}
}
