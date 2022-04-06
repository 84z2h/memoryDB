package com.dant.storage;

import java.util.List;
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

//    public String [] select(String column_name){
//        String []newdata;
//        for(int i = 0; i < data.length; i++){
//            Column [] columns= Table.getColumns();
//            if(columns[i].getName().equals(column_name)){
//                System.out.println("COLUMN MATCH");
//                for(int j = 0; j < data.length; j++){
//                    newdata[j] = data[j][i];
//                }
//            }
//        }
//        return data[0];
//    }

}
