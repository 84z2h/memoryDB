package com.dant.entity;

import com.dant.storage.BasicStorage;
import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileStore;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;

public class Utils{
    private static long start_timer;
    private static long stop_timer;

    /* === CSV Loading... === */
    public static Table loadTable(String csv_filename, int mode) throws IOException {
        // MODE == 0 -> Creation de table
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + csv_filename + ".csv"));
        String s = null;
        // TIMER START
        Utils.start();

        s = in.readLine();
        String[] columnsCsv = s.split(",");
        // Create Table
        Table table;
        if(mode==0) {
            table = new Table(csv_filename);
            BasicStorage.setTable(table);
        }else{
            table = BasicStorage.getTable(csv_filename);
        }

        // add columns to table
        if(mode==0) {
            for (int i = 0; i < columnsCsv.length; i++) {
                // MANQUE PARSING
                BasicStorage.setColumn(csv_filename, new Column(columnsCsv[i], "String"));
            }
        }

        if (mode == 0) {
            Utils.pause();
            System.out.println("Time : " + Utils.getTime()+" ms");
            System.out.println("end of loading columns");
            return table;
        }
        s = in.readLine();
        Long i= Long.valueOf(0);
        int j;
        // load lines
        while ((s = in.readLine()) != null && i < 2) {
            String[] line = s.split(",");
            for(j = 0; j < columnsCsv.length;  j++) {

                table.getColumns().get(columnsCsv[j]).getData().add(line[j]);
            }
            i++;
        }

        // TIMER END
        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end of loading data");
        return table;
    }
    /* === CSV Loading... === */

    public static String buildStringFromData(String [][] data){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                res = res.append(data[i][j]).append(" ");
            }
            res = res.append("\n");
        }
        return res.toString();
    }

    /* === TIMER === */
    public static void start(){
        stop_timer=0;
        start_timer=System.currentTimeMillis();
    }
    public static void pause(){
        if(start_timer==0) return;
        stop_timer= System.currentTimeMillis();
    }
    public static long getTime(){
        return stop_timer - start_timer;
    }
    /* === TIMER === */
    /*
    public static Table loadTableFromData(String tablecsv) throws IOException {
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\"+tablecsv));

        String s = null;
        s=in.readLine();

        String[] columnsCSV = s.split(",");
        Column[] columns =  new Column[columnsCSV.length];
        System.out.println(Arrays.toString(columnsCSV));
        for(int i = 0; i < columnsCSV.length; i++){
            columns[i] = new Column(columnsCSV[i], "String");
        }

        s=in.readLine();
        String[][] data = new String[100][columns.length];
        int i = 0;
        while ((s=in.readLine())!=null && i < 100) {
            String[] line = s.split(",");
            data[i]=line;
            i++;
            //for(int j = 0; j < line.length; j++){
                //System.out.print(line[j]+" ");
            //}
            //System.out.println();
        }
        BasicStorage basicStorage = new BasicStorage(data);
        Table table = new Table(columns, tablecsv.replace(".csv",""), basicStorage);

        System.out.println("end of loading data");

        return table;
    }
    public static String buildStringFromData(String [][] data){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                res = res.append(data[i][j]).append(" ");
            }
            res = res.append("\n");
        }
        return res.toString();
    }
    */
}
