package com.dant.entity.columns;

import java.util.ArrayList;

public class ColumnDouble extends Column{
    private ArrayList<String> dataString = new ArrayList<>();

    public ColumnDouble(String name, String type){
        super(name, type);
    }

    public ArrayList<String> getDataString(){
        return dataString;
    }

}
