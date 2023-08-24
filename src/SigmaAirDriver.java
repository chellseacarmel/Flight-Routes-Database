import java.io.*;
import java.util.Scanner;

/**
 * Chellsea Robinson
 * ID: 112169613
 * Recitation : 08 Daniel Calabria
 *
 * Driver method to run the class
 */
public class SigmaAirDriver {
    public static void main(String[] args) {
        System.out.println(new File(".").getAbsolutePath());
        String o;
        boolean status=true;
        String filename="";
        SigmaAir obj;
        try {
            FileInputStream file = new FileInputStream("sigma_air.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            obj = (SigmaAir) fin.readObject();
            fin.close();
            System.out.println("Successfully loaded contents of sigma_air.obj.");
        } catch(IOException | ClassNotFoundException e){
            System.out.println("sigma_air.obj is not found. A new object will be created.");
            obj =new SigmaAir();
        }
        Scanner stdin=new Scanner(System.in);
        while (status) {
            System.out.println("\n(A) Add City\n" +
                    "(B) Add Connection\n" +
                    "(C) Load all Cities\n" +
                    "(D) Load all Connections\n" +
                    "(E) Print all Cities\n" +
                    "(F) Print all Connections\n" +
                    "(G) Remove Connection\n" +
                    "(H) Find Shortest Path\n" +
                    "(Q) Quit" );
            System.out.println("Enter selection:");
            o=stdin.nextLine();
            switch(o.toUpperCase()) {
                case "A":
                    System.out.println("Enter the name of the city: ");
                    String city=stdin.nextLine();
                    obj.addCity(city);
                    break;
                case"B":
                    System.out.println("Enter the source city: ");
                    String source=stdin.nextLine();
                    System.out.println("Enter the destination city");
                    String dest=stdin.nextLine();
                    obj.addConnection(source,dest);
                    break;
                case "C":
                    System.out.println("Enter the filename");
                    String fileName=stdin.nextLine();
                    obj.loadAllCities(fileName);
                    break;
                case "D":
                    System.out.println("Enter the filename");
                    String FileName=stdin.nextLine();
                    obj.loadAllConnections(FileName);
                    break;
                case "E":
                    boolean t=true;
                    String oe="";
                    while (t) {
                        System.out.println("\n(EA) Sort Cities by Name\n" +
                                "(EB) Sort Cities by Latitude\n" +
                                "(EC) Sort Cities by Longitude\n" +
                                "(Q) Quit");
                        System.out.println("Enter the selection:");
                        oe = stdin.nextLine();
                        switch (oe.toUpperCase()) {

                            case "EA":
                                obj.printAllCities(new NameComparator());
                                break;
                            case "EB":
                                obj.printAllCities(new LatComparator());
                                break;
                            case "EC":
                                obj.printAllCities(new LngComparator());
                                break;
                            case "Q":
                                t = false;
                                break;
                        }
                    }
                    break;
                case"F":
                    obj.printAllConnections();
                    break;
                case"G":
                    System.out.println("Enter the source city: ");
                    String src=stdin.nextLine();
                    System.out.println("Enter the destination city");
                    String dst=stdin.nextLine();
                    obj.removeConnection(src,dst);
                    break;
                case"H":
                    System.out.println("Enter the source city: ");
                    String s=stdin.nextLine();
                    System.out.println("Enter the destination city");
                    String d=stdin.nextLine();
                   System.out.println(obj.shortestPath(s,d));
                    break;
                case"Q":
                    status=false;
                    try {
                        FileOutputStream file = new FileOutputStream("sigma_air.obj");
                        ObjectOutputStream fout = new ObjectOutputStream(file);
                        fout.writeObject(obj); //Writes myLibrary to filename.obj
                        fout.close();
                    } catch (IOException e){
                        System.out.println("Exception while writing file");
                    }
                    System.out.println("SigmaAir is saved into file sigma_air.obj");
                    System.out.println("Program terminating...");
                    break;

            }
        }
    }
}
