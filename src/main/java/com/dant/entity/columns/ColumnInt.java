package com.dant.entity.columns;

import java.util.ArrayList;

public class ColumnInt extends Column{
    private ArrayList<String> dataString = new ArrayList<>();

    public ColumnInt(String name, String type){
        super(name, type);
    }

    public ArrayList<String> getDataString(){
        return dataString;
    }

}
