import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import com.dant.entity.Trip;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        String st = "https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2009-01.csv";
        URL stockURL = new URL(st);
        BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
        /*
        String s = in.readLine();
        System.out.println(s);
        s = in.readLine();
        System.out.println(s);
        s = in.readLine();
        System.out.println(s);
        */
        String s = null;
        s=in.readLine();
        s=in.readLine();
        int nbtrip = 0;
        ArrayList<Trip> ListTrip  = new ArrayList<Trip>();
        while ((s=in.readLine())!=null && nbtrip<10000) {
            String[] line = s.split(",");
            //Trip trip = new Trip(line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7],line[8],line[9],line[10],line[11],line[12],line[13],line[14],line[15],line[16],line[17]);
            Trip trip = new Trip(line);
            //System.out.println(trip);
            ListTrip.add(trip);
            nbtrip += 1;
            //System.out.println(s);
        }
        System.out.println(ListTrip.get(800));
        System.out.println("fin");
    }
}