import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class mainTSP {
	public static void main(String[] args) throws FileNotFoundException {
		Graph tree = FileParser.parserFile();
		
	        System.out.println();
	        
	        double[][] cost = tree.getWeighArray();

	        hamiltonianCycle tp = new hamiltonianCycle(tree);
	        System.out.println("Hamiltonan Cycle(not necessarily optimum):");
	        LinkedList result = tp.getTree();
	        for (int i = 0; i <  result .size(); i++) {
	            System.out.print( result .get(i) + " ");
	        }
	        System.out.println("");
	        System.out.print("Length of Cycle: ");
	        System.out.println(tp.getCycleLength() + " miles");
	        System.out.println("");
	        System.out.println("");
	        System.out.println("Looking at every permutation to find the optimal solution");
	        OptimalCycle treeCycle = new OptimalCycle(tree);
	        System.out.println("The best permutation");
	        LinkedList result2 = treeCycle.getTree();
	        for (int i = 0; i <  result2 .size(); i++) {
	            System.out.print( result2 .get(i) + " ");
	        }
	        System.out.println("");
	        System.out.println("");
	        System.out.print("Optimal Cycle length = ");
	        System.out.println(treeCycle.getLength() + " miles");
	}

}
