import java.util.Comparator;

public class cost_compare implements Comparator<extended_node> {
	static String s = "";

	/*
	 * this method is used to compare two nodes regarding their path cost and
	 * heuristic values
	 */
	public int compare(extended_node x, extended_node y) {

		int ret = 0;
		if (s.equals("adm")) {
			if (x.admissible_heur < y.admissible_heur) {
				ret = -1;
			}
			if (x.admissible_heur >= y.admissible_heur) {
				ret = 1;
			}
		}
		if (s.equals("not_adm")) {
			if (x.not_admissible_heur < y.not_admissible_heur) {
				ret = -1;
			}
			if (x.not_admissible_heur >= y.not_admissible_heur) {
				ret = 1;
			}
		}
		if (s.equals("AS")) {
			if ((x.admissible_heur + x.path_cost) < (y.admissible_heur + y.path_cost)) {
				ret = -1;
			}
			if ((x.admissible_heur + x.path_cost) >= (y.admissible_heur + y.path_cost)) {
				ret = 1;
			}
		}
		return ret;
	}
}