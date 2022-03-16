package com.dant.entity;

import java.util.List;

public class BasicStorage {
    private String[][] data;

    public BasicStorage(String[][] data) {
        this.data = data;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                res = res + data[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

    public List<String> select(String column_name){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                
            }
        }
    }

}
