package com.dant.entity;

import com.dant.storage.BasicStorage;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.lang.Math;

public class Column {
    private String name;
    private String type;
    private final ArrayList<Object> data = new ArrayList<>();

    public Column(String name, String type){
        this.name=name;
        this.type=type;
    }

    public String getName() { return name; }
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Object> getData(){
        return data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        String s = name+" "+type+" "+data.size();
        return s;
    }

    public String optimizeValue(String s){
        Object o = s;
        switch(type){
            case "float":
                Float f = Float.parseFloat(s);
                s = String.format("%.2f",f);
                break;
            default:
                break;
        }
        return s;
    }
}
