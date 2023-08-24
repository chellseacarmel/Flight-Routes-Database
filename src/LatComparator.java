import java.util.Comparator;
import latlng.LatLng;
/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * City class
 */
public class LatComparator  implements Comparator<City> {

    /**
     * Compare method to compare city latitudes.
     * @param o1
     * @param o2
     * @return
     */
    public int compare(City o1, City o2) {
        LatLng a = o1.getLocation();
        a.getLat();
       if(o1.getLocation().getLat() > o2.getLocation().getLat()){
            return 0;
        }
        else {
            return -1;
        }

    }
}

