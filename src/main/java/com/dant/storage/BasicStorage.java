package com.dant.storage;

import java.util.Arrays;
import java.util.List;

import com.dant.entity.Column;
import com.dant.entity.Table;
public class BasicStorage {
    private String[][] data;

    public BasicStorage(String[][] data) {
        this.data = data;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                res = res.append(data[i][j]).append(" ");
            }
            res = res.append("\n");
        }
        return res.toString();
    }

    public String [][] select(String column_name){
        String [][]newdata = new String[10000][1];
        Column[] columns= Table.getColumns();
        for(int i = 0; i < columns.length; i++){
            if(columns[i].getName().equals(column_name)){
                System.out.println("COLUMN MATCH");
                for(int j = 0; j < data.length; j++){
                    newdata[j][0] = data[j][i];
//                    System.out.println(data[j][i]);
                }
            }
        }
        return newdata;
    }

    public String [][] select(String [] columnstab){
        String [][]newdata = new String[100000][columnstab.length];
        Column[] columns = Table.getColumns();
        for(int i = 0; i< columnstab.length; i++){
            for(int j = 0; j < columns.length; j++){
                if(columns[j].getName().equals(columnstab[i])){
                    System.out.println("COLUMN MATCH WITH " + columns[j].getName());
                    for(int d = 0; d < data.length; d++){
                        newdata[d][i] = data[d][j];
                    }
                }
            }
        }
        return newdata;
    }

}
