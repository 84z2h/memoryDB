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

    public List<String[]> getData() {
        return data;
    }
    public void setData(int i,String[] newData) {
        data.set(i, newData);
    }

    public void addElementToLine(int i,String elem){
        List<String> list = Arrays.asList(data.get(i));
        list.add(elem);
        this.setData(i,list.toArray(new String[0]));
    }

    public String[] getLine(int i){
        return data.get(i);
    }

    public int getSize(){
        return data.size();
    }

    public void addString(String[] str){
        data.add(str);
    }
}
