import java.util.LinkedList;

public class OptimalCycle {
	private double[][] distance;
	private LinkedList tree;
	private Graph graph;
	private LinkedList<String> alltree  = new LinkedList();
	private Double treeLength = 0.0;
	private boolean on = true;
	
	
	public OptimalCycle(Graph graph) {
		this.distance = graph.getWeighArray();
		this.graph = graph;
		int[] vertex = new int[graph.getSize()];
		for(int i = 0 ; i<graph.getSize(); i++) {
			vertex[i] = i;
		}
		int start = 0;
		Permutate(vertex, start, graph.getSize()-1);
		calculateOptimalCycle();
		
	}
	
	  public LinkedList getTree() {
		  if(on) {
  		tree.add((Integer) tree.get(0));
  		on = false;
  		return tree ;
		  }
		  else {
			  return tree ;
		  }
  }

	    public double getLength() {
	        return treeLength;
	    }
		
	
    private void Permutate(int[] a, int start, int end) {
    	  if (start == end) {
              String result = "";
              for (int i = 0; i < a.length; i++) {
                  result += a[i] + ",";
              }
                 alltree.add(result);
          } else {
              for (int i = start; i <= end; i++) {
                  swap(a, start, i);
                  Permutate(a, start + 1, end);
                  swap(a, i, start);
              }
          }
       }
    
    private void swap(int[] array, int i, int j) {

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void calculateOptimalCycle() {

        for (int i = 0; i < alltree.size(); i++) {

            String[] possibleTour = alltree.get(i).split(",");
            LinkedList current = new LinkedList();
            for (int j = 0; j < possibleTour.length; j++) {
                current.add(Integer.valueOf(possibleTour[j]));
            }
            

            double currentCycleLength = getCycleLength(current);
            if (treeLength <= 0.000000000000000001) {
                tree = current;
                treeLength = currentCycleLength;
            }
            if (treeLength > currentCycleLength) {
                tree = current;
                treeLength = currentCycleLength;
            }
            

        }
    }
	
    
	
	 public double getCycleLength(LinkedList tree) {
	        double result = 0;
	        int first = 0;
	        int next = 0;
	        for (int i = 0; i < tree.size() - 1; i++) {
	            first = (Integer) tree.get(i);
	            next = (Integer) tree.get(i + 1);
	            result += distance[first][next];
	        }
	        result += distance[next][(Integer) tree.get(0)];
	        return result * 0.00018939;
	    }
	 
	
	  
}
