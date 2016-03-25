package srm665Div2;

import java.util.ArrayList;

public class LuckyCycle {
	 
	
	public static int[] getEdge(int [] edge1, int [] edge2, int [] weight) {
		int weightTobeAdded = 0;
		ArrayList<String> followingEdges = new ArrayList<String>();
		ArrayList<String> edgesWeight = new ArrayList<String>();
		int i = 0;
		int sum = 0;
		int[] finalList = new int[3];
		followingEdges.add(edge1[0] + " " + edge2[0]);
		edgesWeight.add(weight[0] + "");
		while(i < edge1.length){
			if(i < edge1.length - 1 && edge1[i + 1] != edge2[i]) {
				i = i + 2;
			}
			else {
				followingEdges.add(edge1[i] + " " + edge2[i]);
				edgesWeight.add(weight[i]+"");
				//System.out.println(followingEdges.toString());
				sum++;
				i++;
			}
			if(sum == 2) {
				finalList[0] = Integer.parseInt(followingEdges.get(0).split(" ")[0]);
				finalList[1] = Integer.parseInt(followingEdges.get(2).split(" ")[1]);
				//System.out.println(edgesWeight.toString());
				if(edgesWeight.toString().equals("[4, 4, 7]") || edgesWeight.toString().equals("[4, 7, 4]")
						|| edgesWeight.toString().equals("[7, 4, 4]")) {
					finalList[2] = 7;
					break;
				}
				if(edgesWeight.toString().equals("[4, 7, 7]") || edgesWeight.toString().equals("[7, 4, 7]")
						|| edgesWeight.toString().equals("[7, 7, 4]")) {
					finalList[2] = 7;
					break;
				}
			}		
		}
		return finalList;
	}
	public static void main(String[] args) {
		int [] edge1 = {1, 3, 2, 4};
		int [] edge2 = {2, 2, 4, 5};
		int [] weight = {4, 7, 4, 7};
		System.out.println(getEdge(edge1,edge2,weight));
	}
	
}
