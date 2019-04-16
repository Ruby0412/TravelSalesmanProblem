import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class mainTSP {
	public static void main(String[] args) throws IOException {
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
        LinkedList normal = tp.getTree();
        String[] normalcoordinate =  Cooridnate(tree,normal);
      
        
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
        LinkedList optimal = treeCycle.getTree();
        String[] optimalco = Cooridnate(tree,optimal);
        for(int i=0;i<normalcoordinate.length;i++){
            String[] location = normalcoordinate[i].split(",");
            String longitude = location[0];
            String latitude = Double.valueOf(location[1])+0.0003+"";
            normalcoordinate[i] = longitude+","+latitude;
        }
        String xml = outputXML(normalcoordinate,optimalco);
        writeFile(xml);

	      
	        
	}

	private static String[] Cooridnate(Graph tree, LinkedList normal) {
		// TODO Auto-generated method stub
		  String[] result = new String[tree.getSize()+1];
		  	
	        for(int i=0;i<normal.size();i++){
	            int label = (Integer)normal.get(i);
	            String longitude = ""+FileParser.getLongitude(label);
	            String latitude = ""+FileParser.getatitude(label);
	            result[i]=longitude+","+latitude;
	         
	        }
	        result[tree.getSize()] = result[0];
	        return result;
	
	}
	 
	private static void writeFile(String xml) throws IOException {
        File file = new File("PGHCrimes.kml");
        try (FileWriter fw = new FileWriter(file, false) 
                ) {
            fw.write(xml);
        }
    }


    private static String outputXML(String[] coordinate1,String[] coordinate2) {
        
        String result;
        XMLversion xml = new XMLversion();
        xml.addLocationmark(coordinate1, "TSP");
        xml.addLocationmark(coordinate2, "Optimal");
        xml.End();
        result = xml.toString();
        return result;
    }

}
