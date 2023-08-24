import latlng.LatLng;

import java.io.Serializable;

/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * City class
 */

public class City implements Comparable, Serializable {
    private String city;
    private LatLng location;
    private int indexPos;
    private static int cityCount;

    /**
     * Empty constructor for City class
     */
    public City() {

    }

    /**
     * Setter method to set the city name
     * @param city
     */
    public void setCity(String city) {
        this.city=city;
    }

    /**
     * Setter method to set indexPos
     * @param indexPos
     */
    public void setindexPos(int indexPos) {
        this.indexPos=indexPos;
    }

    /**
     * Setter method to set city count
     * @param cityCount
     */
    public void setcityCount(int cityCount)	{
        City.cityCount=cityCount;
    }

    /**
     * Setter method to set location
     * @param location
     */
    public void setLocation(latlng.LatLng location){
        this.location=location;
    }

    /**
     * Getter method to get city name
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter method to get index position
     * @return
     */
    public int getindexPos() {
        return indexPos;
    }

    /**
     * Getter method to get the count of the city
     * @return
     */
    public int getcityCount() {
        return cityCount;
    }

    /**
     * Getter method to get location
     * @return
     */
    public LatLng getLocation(){
        return location;
    }

    /**
     * Compare to method for the object
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
         City other=(City)o;
         if (this.city.equals(other.getCity()))
             return 0;
         else
             return -1;

    }

}

