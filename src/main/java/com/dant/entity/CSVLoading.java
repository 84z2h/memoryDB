package com.dant.entity;

import com.dant.storage.BasicStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class CSVLoading {

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

    public static Table createTable(String csv_filename) throws IOException {
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + csv_filename + ".csv"));
        String s = null;
        // TIMER START
        TimerManage.start();
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
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end of loading columns");
        return table;
    }

    public static Table insertTable(String csv_filename) throws IOException {
        // MODE == 0 -> Creation de table
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + csv_filename + ".csv"));
        String s = null;
        // TIMER START
        TimerManage.start();

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
            if(i % 100000 == 0){
                System.out.println(i + " lines inserted");
            }
            String[] line = s.split(",");
            for(j = 0; j < columnsCsv.length;  j++) {
                //table.getColumns().get(columnsCsv[j]).getData().add(line[j]);
                Column column = table.getColumns().get(columnsCsv[j]);
                List<Object> row = column.getData();
                row.add(column.optimizeValue(line[j]));
            }
            i++;
        }
        System.out.println(i  + " lines have been inserted successfully");
        table.setSize(table.getSize()+i);
        // TIMER END
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end of loading data");
        return table;
    }
}
