package com.dant.entity;

import com.dant.storage.BasicStorage;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Locale;

import static java.lang.Math.abs;

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

    public Object optimizeValue(String s){
        switch(type){
            case "double":
            case "float":
                float f = Float.parseFloat(s);
                s = String.format("%.2f",f);
                f = Float.valueOf(s.replace(',','.'));
                return f;
            case "int":
                int i = Integer.parseInt(s);
                if (abs(i) < 10) {
                    return s;
                }
                else if (abs(i)<32766){
                    short sh = Short.parseShort(s);
                    return sh;
                }
                else if (abs(i)<2147483646){
                    return i;
                }
                else{
                    long l = Long.parseLong(s);
                    return l;
                }
            case "boolean":
                boolean b = Boolean.parseBoolean(s);
                return b;
            default:
                return s.toLowerCase();
        }
    }
}
