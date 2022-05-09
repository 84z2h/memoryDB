package com.dant.entity;

import com.dant.storage.BasicStorage;
import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileStore;
import java.util.*;

public class Utils{
    private static long start_timer;
    private static long stop_timer;


    public static String indentifyType(int columnId){
        String type;
        switch(columnId){
            case 0,1,2,7,8,11,14 :
                type = "String";
                break;
            case 3:
                type = "int";
                break;
            default:
                type = "float";
                break;
        }
        return type;
    }

    /* === CSV Loading... === */
    public static Table createTable(String csv_filename) throws IOException {
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + csv_filename + ".csv"));
        String s = null;
        // TIMER START
        Utils.start();
        s = in.readLine();
        String[] columnsCsv = s.split(",");
        // Create Table
        Table table;
        table = new Table(csv_filename);
        BasicStorage.setTable(table);
        for (int i = 0; i < columnsCsv.length; i++) {
            // MANQUE PARSING
            String type = indentifyType(i);
            BasicStorage.setColumn(csv_filename, new Column(columnsCsv[i], type));
        }
        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end of loading columns");
        return table;
    }
    public static Table insertTable(String csv_filename) throws IOException {
        // MODE == 0 -> Creation de table
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + csv_filename + ".csv"));
        String s = null;
        // TIMER START
        Utils.start();

        s = in.readLine();
        String[] columnsCsv = s.split(",");
        // Create Table
        Table table = BasicStorage.getTable(csv_filename);
        // add columns to table
        s = in.readLine();
        long i = 0;
        int j;
        // load lines
        while ((s = in.readLine()) != null && i < 1000000) {
            String[] line = s.split(",");
            for(j = 0; j < columnsCsv.length;  j++) {
                //table.getColumns().get(columnsCsv[j]).getData().add(line[j]);
                Column column = table.getColumns().get(columnsCsv[j]);
                List<Object> row = column.getData();
                row.add(column.optimizeValue(line[j]));
            }
            i++;
        }
        table.setSize(table.getSize()+i);
        // TIMER END
        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end of loading data");
        return table;
    }
    /* === CSV Loading... === */

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
}
