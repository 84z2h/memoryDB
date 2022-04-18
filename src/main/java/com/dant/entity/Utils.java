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
        while ((s = in.readLine()) != null && i < 100000) {
            String[] line = s.split(",");
            for(j = 0; j < columnsCsv.length;  j++) {
                table.getColumns().get(columnsCsv[j]).getData().add(line[j]);
            }
            table.setSize(table.getSize()+1);
            i++;
        }

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
