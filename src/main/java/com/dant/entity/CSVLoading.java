package com.dant.entity;

import com.dant.entity.columns.*;
import com.dant.storage.BasicStorage;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class CSVLoading {

    public static String identifyType(int columnId){
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
            String type = identifyType(i);
            BasicStorage.setColumn(csv_filename, new Column(columnsCsv[i], type));
        }
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end of loading columns");
        in.close();
        return table;
    }

    public static String insertTable(String csv_filename, int max_size, InputStream input) throws IOException {
        System.out.println("start loading data");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String s = null;
        // TIMER START
        TimerManage.start();
        s = in.readLine();
        String[] columnsCsv = s.split(",");
        // Get Table
        Table table = BasicStorage.getTable(csv_filename);
        // add columns to table
        s = in.readLine();
        long i = 0;
        int j;
        // load lines
        Column column;
        ArrayList<Object> row;
        while ((s = in.readLine()) != null && i < max_size) {
            if(i % 100000 == 0){
                System.out.println(i + " lines inserted");
            }
            String[] line = s.split(",");
            for(j = 0; j < columnsCsv.length;  j++) {
                column = table.getColumns().get(columnsCsv[j]);
                row = column.getData();
                row.add(column.optimizeValue(line[j]));
            }
            i++;
        }
        System.out.println(i  + " lines have been inserted successfully");
        table.setSize(table.getSize()+i);
        // TIMER END
        TimerManage.pause();
        String Time = "Elapsed Time : " + TimerManage.getTime()+" ms\n";
        Time += "Lines inserted : " + i;
        System.out.println(Time);
        in.close();
        return Time;
    }

    public static String insertTable2(String csv_filename, int max_size, InputStream input) throws IOException {
        System.out.println("start loading data");
        String s = null;
        int j;
        long i = 0;
        // TIMER START
        TimerManage.start();
        // Get Table
        Table table = BasicStorage.getTable(csv_filename);
        BufferedReader temp = new BufferedReader(new InputStreamReader(input));
        String firstLine = temp.readLine();
        temp.close();
        for(String column_name: table.getColumns().keySet()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            s = in.readLine();  s = in.readLine();
            // load lines
            i=0;
            j = getColumnNumber(column_name,firstLine);
            while ((s = in.readLine()) != null && i < max_size) {
                if (i % 100000 == 0 && i != 0) {
                    System.out.println(i + " lines inserted");
                }
                String[] line = s.split(",");
                addElement(table,column_name,line[j]);
                i++;
            }
            j++;
            in.close();
        }
        System.out.println(i  + " lines have been inserted successfully");
        table.setSize(table.getSize()+i);
        // TIMER END
        TimerManage.pause();
        String Time = "Elapsed Time : " + TimerManage.getTime()+" ms\n";
        Time += "Lines inserted : " + i;
        System.out.println(Time);
        return Time;
    }

    public static int getColumnNumber(String column_name, String firstLine){
        List<String> head = Arrays.asList(firstLine.split(","));
        return head.indexOf(column_name);

    }

    public static void addElement(Table table,String column_name, String data) {
        String type = table.getColumns().get(column_name).getType();
        switch (type) {
            case "float":
                ColumnFloat colFloat = (ColumnFloat) table.getColumns().get(column_name);
                colFloat.add(Float.parseFloat(data));
                break;
            case "String":
                ColumnString colString = (ColumnString) table.getColumns().get(column_name);
                colString.add(data);
                return;
            case "byte":
                ColumnByte colByte = (ColumnByte) table.getColumns().get(column_name);
                colByte.add(Byte.parseByte(data));
                break;
            case "int":
                ColumnInt colInt = (ColumnInt) table.getColumns().get(column_name);
                colInt.add(Integer.parseInt(data));
                break;
            case "double":
                ColumnDouble colDouble = (ColumnDouble) table.getColumns().get(column_name);
                colDouble.add(Double.parseDouble(data));
                break;
            case "short":
                ColumnShort colShort = (ColumnShort) table.getColumns().get(column_name);
                colShort.add(Short.parseShort(data));
                break;
            case "long":
                ColumnLong colLong = (ColumnLong) table.getColumns().get(column_name);
                colLong.add(Long.parseLong(data));
                break;
            case "boolean":
                ColumnBoolean colBoolean = (ColumnBoolean) table.getColumns().get(column_name);
                colBoolean.add(Boolean.parseBoolean(data));
                break;
            default:
                System.out.println("Type non pris en charge.");
                break;
        }
    }

}
