import latlng.LatLng;

import java.util.Comparator;
/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * Longitude comparator class
 */
public class LngComparator implements Comparator<City> {
    /**
     * Compare method to compare city longitudes.
     * @param o1
     * @param o2
     * @return
     */
    public int compare(City o1, City o2) {
        LatLng a = o1.getLocation();
        a.getLng();
        if(o1.getLocation().getLng() > o2.getLocation().getLng()){
            return 0;
        }
        else {
            return -1;
        }

    }

}
