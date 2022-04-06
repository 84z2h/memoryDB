package com.dant.entity;

import com.dant.storage.BasicStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Utils {

    public static Table loadTableFromData() throws IOException {
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("yellow_tripdata_2009-01.csv"));

        String s = null;
        s=in.readLine();

        String[] columnsCSV = s.split(",");
        Column[] columns =  new Column[columnsCSV.length];
        System.out.println(Arrays.toString(columnsCSV));
        for(int i = 0; i < columnsCSV.length; i++){
            columns[i] = new Column(columnsCSV[i], "String");
        }

        s=in.readLine();
        String[][] data = new String[1000000][columns.length];
        int i = 0;
        while ((s=in.readLine())!=null && i < 1000000) {
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

        System.out.println("end of loading data");

        return table;
    }
}
