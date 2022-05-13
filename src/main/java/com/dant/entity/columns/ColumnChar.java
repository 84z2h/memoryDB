package com.dant.entity.columns;

import java.util.ArrayList;

public class ColumnChar extends Column{
    private ArrayList<String> dataChar = new ArrayList<>();

    public ColumnChar(String name, String type){
        super(name, type);
    }

    public ArrayList<String> getDataString(){
        return dataChar;
    }

}
