import com.cse214.geocoder.GeocodeResponse;
import com.cse214.geocoder.Geocoder;
import latlng.LatLng;


public class test {
    public static void main(String[] args){

        String cityFrom = "New York";
        String cityTo = "Beijing";
        Geocoder geocoder = new Geocoder();
        GeocodeResponse from;
        GeocodeResponse to;
        double latFrom;
        double lngFrom;
        double latTo;
        double lngTo;
        LatLng src = new LatLng();
        LatLng dest = new LatLng();

        try {

            from = geocoder.geocode(cityFrom);
            latFrom = from.getLat();
            lngFrom = from.getLng();
            // System.out.println(addr + " " + latFrom + " " + lngFrom);
            to = geocoder.geocode(cityTo);
            latTo = from.getLat();
            lngTo = from.getLng();

            src.setLat(latFrom);
            src.setLng(lngFrom);
            dest.setLat(latTo);
            dest.setLng(lngTo);
        }catch (Exception e) {
            System.out.println();
        }
        double distance = LatLng.calculateDistance(src, dest);


//        LatLng src = new LatLng(30, 50); // create a LatLng object with initial value
//        LatLng dest = new LatLng();      // create LatLng object with default value 0
//        dest.setLat(-25);
//        dest.setLng(45.552);
//        double distance = LatLng.calculateDistance(src, dest); // invokes static method calculateDistance()
        System.out.println(distance);   // prints the distance (calculated in kilometer)
    }

}
