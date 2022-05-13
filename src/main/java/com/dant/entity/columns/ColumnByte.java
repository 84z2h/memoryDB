package com.dant.entity.columns;

import java.util.ArrayList;

public class ColumnByte extends Column{
    private ArrayList<String> dataString = new ArrayList<>();

    public ColumnByte(String name, String type){
        super(name, type);
    }

    public ArrayList<String> getDataString(){
        return dataString;
    }

}
