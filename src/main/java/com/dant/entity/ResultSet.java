package com.dant.entity;

import java.util.ArrayList;
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
    public void setData(List<String[]> data) {
        this.data = data;
    }

    public void addString(String[] str){
        data.add(str);
    }
}
