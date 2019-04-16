import java.util.*;

public class XMLversion {
  
    private static final String start = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
            + "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n"
            + "<Document>\n"
            + "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n"
            + "<LineStyle>\n"
            + "<color>73FF0000</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>\n"
            + "<Style id=\"style5\">\n"
            + "<LineStyle>\n"
            + "<color>507800F0</color>\n"
            + "<width>5</width>\n"
            + "</LineStyle>\n"
            + "</Style>\n";
    String xml;
   
    public XMLversion() {
        xml = start;
    }
    
    
    public void addLocationmark(String[] coordinates,String type){
        String placemark = markLocation(coordinates,type);
        xml += placemark;
    }
    
    private String markLocation(String[] coordinates,String type) {
        String allCoordinates = "";
        for(String coordinate:coordinates){
            allCoordinates += coordinate+",0.000000\n";
        }
        String path ="";
        String style = "";
        if(type.equals("Optimal")){
            path = "Optimal Path";
            style = "#style5";
        }
        else{
            path = "TSP Path";
            style = "#style6";
        }
        String str = "<Placemark>\n"
                + "<name>"+path+"</name>\n"
                + "<description>"+path+"</description>\n"
                + "<styleUrl>"+style+"</styleUrl>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n"
                + "<coordinates>\n"
                + allCoordinates
                + "</coordinates>\n"
                + "</LineString>\n"
                + "</Placemark>";
        return str;
    }
    
 
    public void End(){
        xml += "</Document>\n" +
        		"</kml>";
    }
 
    public String toString() {
        return xml;
    }

}
