import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import com.cse214.geocoder.GeocodeResponse;
import com.cse214.geocoder.Geocoder;
import latlng.LatLng;
/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * Sigma Air class
 */
public class SigmaAir implements Serializable {
    private ArrayList<City> cities;
    public static final int MAX_CITIES =100;
    private double[][] connections;

    /**
     * Empty constructor for SigmaAir
     */
    public SigmaAir() {
        cities = new ArrayList<City>();
        connections= new double [100][100];
    }

    /**
     * Method to add the city based on its name
     * @param city
     */
    public void addCity(String city) {
        City temp=new City();
        temp.setCity(city);
        cities.add(temp);
        Geocoder geocoder = new Geocoder();
        GeocodeResponse c;
        c=geocoder.geocode(city);
        double lat,lng;
        lat=c.getLat();
        lng=c.getLng();
        LatLng t=new LatLng();
        t.setLat(lat);
        t.setLng(lng);
        temp.setLocation(t);
        System.out.println(city+" has been added: ("+lat+", "+ lng+")");
    }

    /**
     * Method to load cities from the file
     * @param filename
     */
    public void loadAllCities(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inStream);
            String data = reader.readLine();
            while (data != null) {
               addCity(data);
                data = reader.readLine();
            }
        }
        catch(IOException e){
                System.out.println("File not found \n");
            }
    }

    /**
     * Method to add a flight connection based ton the To and From city name
     * @param cityFrom
     * @param cityTo
     */
    public void addConnection(String cityFrom, String cityTo) {
        try {
            Geocoder geocoder = new Geocoder();
            GeocodeResponse from;
            GeocodeResponse to;
            double latFrom;
            double lngFrom;
            double latTo;
            double lngTo;
            from = geocoder.geocode(cityFrom);
            latFrom = from.getLat();
            lngFrom = from.getLng();
            to = geocoder.geocode(cityTo);
            latTo = to.getLat();
            lngTo = to.getLng();
            LatLng src =new LatLng();
            LatLng dest=new LatLng();
            src.setLat(latFrom);
            src.setLng(lngFrom);
            dest.setLat(latTo);
            dest.setLng(lngTo);
            double distance = LatLng.calculateDistance(src, dest);
            Iterator<City> a= cities.iterator();
            City temp=new City();
            int counter=0;
            int indexTo=0;
            int indexFrom=0;
            while (a.hasNext()){
                temp=a.next();
                if (temp.getCity().equals(cityFrom)){
                    indexFrom=counter;
                }
                else if (temp.getCity().equals(cityTo)){
                     indexTo=counter;
                }
                counter++;
            }
            boolean stat1=false;
            boolean stat2=false;
            for (int d=0;d<cities.size();d++) {
                if (cities.get(d).getCity().equals(cityFrom)){
                        stat1=true;
                }
            }
            for (int e=0;e<cities.size();e++) {
                if (cities.get(e).getCity().equals(cityTo)){
                    stat2=true;
                }
            }
                if(stat1&& stat2) {
                    connections[indexFrom][indexTo] = distance;
                    System.out.println(cityFrom + " --> " + cityTo + " added: " +
                            connections[indexFrom][indexTo]);
                }
                else{
                    System.out.println("Error adding connection: "+ cityFrom+" --> "+cityTo);
                }
        } catch (Exception e) {
            // error handling goes here
            System.out.println(cityFrom+" --> "+cityTo+ " could not be added");
        }
    }

    /**
     * Method to load the file connections from the file
     * @param filename
     */
    public void loadAllConnections(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inStream);
            String data = reader.readLine();
            while (data != null) {
               String From= data.substring(0,data.indexOf(","));
               String To=data.substring(data.indexOf(",")+1,data.length());
               addConnection(From,To);
               data = reader.readLine();
            }
        }
        catch(IOException e){
            System.out.println("File not found \n");
        }
    }

    /**
     * Method to remove a flight connection
     * @param cityFrom
     * @param cityTo
     */
    public void removeConnection(String cityFrom, String cityTo){
        Iterator<City> a= cities.iterator();
        City temp=new City();
        int counter=0;
        int indexTo=0;
        int indexFrom=0;
        while (a.hasNext()){
            temp=a.next();
            if (temp.getCity().equals(cityFrom)){
                indexFrom=counter;
            }
            else if (temp.getCity().equals(cityTo)){
                indexTo=counter;
            }
            counter++;
        }
        double pInfiniteDouble = Double.POSITIVE_INFINITY;
        connections[indexFrom][indexTo]=pInfiniteDouble;
        System.out.println("Connection from "+ cityFrom+ " to "+ cityTo+ " has been removed!");
    }

    /**
     * Method to find the shortest path between the airports.
     * @param cityFrom
     * @param cityTo
     * @return
     */
    public String shortestPath(String cityFrom, String cityTo) {
         double [][] dist=new double[cities.size()][cities.size()];
         int [][] next =new int [cities.size()][cities.size()];
          for (int u=0;u<cities.size();u++) {
              for (int v=0;v<cities.size();v++) {
                  if (connections[u][v] == 0|| connections[u][v] ==Double.POSITIVE_INFINITY)
                  {
                      dist[u][v] = Double.POSITIVE_INFINITY;
                      next[u][v]=-1;
                  }
                  else
                  {
                      dist[u][v]=connections[u][v];
                      next[u][v]=v;
                  }

              }
          }
          for (int k=0;k<cities.size();k++){
              for (int i=0;i<cities.size();i++){
                  for (int j=0;j<cities.size();j++){
                      if (dist[i][k]+dist[k][j]<dist[i][j]){
                          dist[i][j] = dist[i][k] + dist[k][j];
                          next[i][j] = next[i][k];
                      }
                  }
              }
          }
        String path="";
        Iterator<City> a= cities.iterator();
        City temp=new City();
        int counter=0;
        int v=0;
        int u=0;
        double distance=0;
        while (a.hasNext()) {
            temp = a.next();
            if (temp.getCity().equals(cityFrom)) {
                u = counter;
            } else if (temp.getCity().equals(cityTo)) {
                v = counter;
            }
            counter++;
        }
          if (next[u][v]==-1){
              System.out.println("Shortest path from "+ cityFrom+" to "+ cityTo+" does not exist!");
              return path;
          }
          path=cityFrom;
          while (u!=v){
              u=next[u][v];
              path+=" --> "+cities.get(u).getCity();
              distance+=connections[u][v];
          }
          return String.format("%-30s%-30s",path,distance);
    }

    /**
     * Method to print all the flight connections
     */
    public void printAllConnections(){
        System.out.println("Connections:");
        String line,print;
        line=print="";
        for(int i=0; i<70; i++){
            line += "-";
        }
        for (int i=0;i<cities.size();i++) {
            for (int j=0;j<cities.size();j++) {
                if ((connections[i][j]!=0)&& (connections[i][j]!=Double.POSITIVE_INFINITY) ){
                    print += String.format("%-50s%-50s", cities.get(i).getCity() + " --> " +
                            cities.get(j).getCity(), connections[i][j]) + "\n";
                }
            }
        }
        System.out.print( String.format("%-50s%-50s","Route","Distance")+
                "\n"+line+"\n"+print);
    }

    /**
     * Method to print all the cities in a specified order.
     * @param comp
     */
    public void printAllCities(Comparator comp){
        Collections.sort(cities,comp);
        String line,print;
        line=print="";
        for(int i=0; i<110; i++){
            line += "-";
        }
        for (int i=0;i<cities.size();i++){
            print+= String.format("%-50s%-30s%-30s",cities.get(i).getCity(),
                    cities.get(i).getLocation().getLat(),
                    cities.get(i).getLocation().getLng())+"\n";
        }
        System.out.print( String.format("%-50s%-30s%-30s","Name",
                "Latitude","Longitude")+"\n"+line+"\n"+print);
    }
}


