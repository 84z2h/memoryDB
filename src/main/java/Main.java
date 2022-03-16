import java.io.*;
import java.net.URL;

import com.dant.entity.*;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        //String st = "https://s3.amazonaws.com/nyc-tlc/trip+data/yellow_tripdata_2009-01.csv";
        //URL stockURL = new URL(st);
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\U1\\Downloads\\yellow_tripdata_2009-01.csv"));

        /*
        String s = in.readLine();
        System.out.println(s);
        s = in.readLine();
        System.out.println(s);
        s = in.readLine();
        System.out.println(s);
        */

        //Table table = new Table();
        String s = null;
        s=in.readLine();

        String[] columnsCSV = s.split(",");
        Column[] columns =  new Column[columnsCSV.length];

        for(int i = 0; i < columnsCSV.length; i++){
            columns[i] = new Column(columnsCSV[i], "String");
        }

        s=in.readLine();
        String[][] data = new String[10000000][columns.length];
        int i = 0;
        while ((s=in.readLine())!=null && i < 10000000) {
            String[] line = s.split(",");
            data[i]=line;
            i++;
            /*
            for(int j = 0; j < line.length; j++){
                //System.out.print(line[j]+" ");
            }*/
            //System.out.println();
        }
        BasicStorage basicStorage = new BasicStorage(data);
        Table table = new Table(columns, "Taxi", basicStorage);

        //System.out.println(ListTrip.get(40000));
        System.out.println("fin");
    }
}