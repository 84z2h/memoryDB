package com.dant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSet {
    private List<String[]> data;

    public ResultSet(){
        data= new ArrayList<String[]>();
    }

    @Override
    public String toString() {
        return "ResultSet{" +
                "data=" + data +
                '}';
    }

    // Getter Data
    public List<String[]> getData() {
        return data;
    }
    public void setData(int i,String[] newData) {
        data.set(i, newData);
    }

    public void addElementToLine(int i,String elem) {
        List<String> list = Arrays.asList(data.get(i));
        list.add(elem);
        this.setData(i, list.toArray(new String[0]));
    }

    // Getter ième ligne Data
    public String[] getLine(int i){
        return data.get(i);
    }

    //Getter size of Data
    public int getSize(){
        return data.size();
    }

    // Ajout d'un élement à Data
    public void addString(String[] str){
        data.add(str);
    }
}
