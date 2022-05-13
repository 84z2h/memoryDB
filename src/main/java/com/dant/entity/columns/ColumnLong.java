package com.dant.entity.columns;

import java.util.ArrayList;

public class ColumnLong extends Column{
    private ArrayList<String> dataString = new ArrayList<>();

    public ColumnLong(String name, String type){
        super(name, type);
    }

    public ArrayList<String> getDataString(){
        return dataString;
    }

}
