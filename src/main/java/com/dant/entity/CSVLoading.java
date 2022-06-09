package com.dant.entity;

import com.dant.entity.columns.*;
import com.dant.storage.BasicStorage;

import java.io.*;

public class CSVLoading {

    // Idenitifie le type de la colunme selon son id
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

    // Gère tout ce qui touche à la création d'une nouvelle table dans le programme
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

    // Gère tout ce qui touche à l'insertion d'une table dans le programme
    public static String insertTable(String csv_filename, int max_size, InputStream input) throws IOException {
        System.out.println("start loading data");
        String s = null;
        int j;
        long i = 0;
        // TIMER START
        TimerManage.start();
        // Get Table
        Table table = BasicStorage.getTable(csv_filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String firstLine = in.readLine();
        while ((s = in.readLine()) != null && i < max_size) {
            if (i % 100000 == 0 && i != 0) {
                System.out.println(i + " lines inserted");
            }
            // load lines
            String[] line = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            for(String column_name: table.getColumns().keySet()) {
                j = Column.getColumnNumber(column_name,firstLine);
                table.getColumns().get(column_name).addElement(line[j]);
            }
            line = null;
            i++;
        }
        in.close();
        System.out.println(i  + " lines have been inserted successfully");
        table.setSize(table.getSize()+i);
        // TIMER END
        TimerManage.pause();
        String Time = "Elapsed Time : " + TimerManage.getTime()+" ms\n";
        Time += "Lines inserted : " + i;
        System.out.println(Time);
        return Time;
    }
}
