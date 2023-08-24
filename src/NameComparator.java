import java.util.Comparator;
/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * Name Comparator class
 */
public class NameComparator implements Comparator<City> {
    /**
     * Compare to method to compare the city name
     * @param o1
     * @param o2
     * @return
     */
    public int compare(City o1, City o2) {
        return (o1.getCity().compareTo(o2.getCity()));
    }
}

