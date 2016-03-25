
public class ProfitCalculator {

	public static int percent(String[] items) {
		double sumOfPaid = 0, sumOfCost = 0;
		for (int i = 0; i < items.length; i++) {
			sumOfPaid = sumOfPaid + Double.parseDouble(items[i].split(" ")[0]); 
			
			sumOfCost = sumOfCost +Double.parseDouble(items[i].split(" ")[1]); 
			
		}
		double margin = ((sumOfPaid - sumOfCost)*100)/sumOfPaid;
		return (int) margin;
	}
}
