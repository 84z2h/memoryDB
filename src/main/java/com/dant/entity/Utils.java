package com.dant.entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static Table loadTableFromData() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\U1\\Downloads\\yellow_tripdata_2009-01.csv"));

        String s = null;
        s=in.readLine();

        String[] columnsCSV = s.split(",");
        Column[] columns =  new Column[columnsCSV.length];

        for(int i = 0; i < columnsCSV.length; i++){
            columns[i] = new Column(columnsCSV[i], "String");
        }

        s=in.readLine();
        String[][] data = new String[10000][columns.length];
        int i = 0;
        while ((s=in.readLine())!=null && i < 10000) {
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

        System.out.println("fin");

        return table;
    }
}
