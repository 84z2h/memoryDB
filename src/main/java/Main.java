import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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
        while ((s=in.readLine())!=null) {
            System.out.println(s);
        }
    }
}