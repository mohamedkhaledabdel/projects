import java.util.ArrayList;


public class mostProfitable {

	public static String bestItem(int[] costs, int[] prices, int[] sales, String[] items) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String bestItem = "";
		for(int i = 0; i < items.length; i++) {
			if((prices[i] - costs[i]) * sales[i] > 0){
			list.add((prices[i] - costs[i]) * sales[i]);
			System.out.println(list.get(i));
			}
		}
		if(getHighestElementIndex(list) >= 0) {
			bestItem = items[getHighestElementIndex(list)];
		}
		return bestItem;
	}
	
	
	public static int getHighestElementIndex(ArrayList<Integer> list) {
		int maxIndex = 0;
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) >= max ){
				max = list.get(i);
				System.out.println(max);
			}				
		}
		
		maxIndex = list.indexOf(max);
		System.out.println(maxIndex);
		return maxIndex;
	}
	
	public static void main(String[] args) {
		int [] costs = new int[1];
		int [] prices = new int[1];
		int [] sales = new int[1];
		String [] items = new String[1];
		costs[0] = 10;
		//costs[1] = 10;
		//costs[2] = 150;
		//costs[3] = 1000;
		prices[0] = 10;
		//prices[1] = 12;
		//prices[2] = 200;
		//prices[3] = 2000;
		sales[0] = 134;
		//sales[1] = 1;
		//sales[2] = 50;
		//sales[3] = 3;
		items[0] = "A";
		//items[1] = "B";
		//items[2] = "CPU";
		//items[3] = "complete machine";
		System.out.println(bestItem(costs, prices, sales, items));
		
		
		
	}
}
