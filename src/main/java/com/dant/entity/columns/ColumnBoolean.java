package com.dant.entity.columns;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanListIterator;


public class ColumnBoolean extends Column{

    private BooleanArrayList dataBool = new BooleanArrayList();

    public ColumnBoolean(String name){
        super(name, "boolean");
    }

    public void add(boolean bool){
        dataBool.add(bool);
    }

    public boolean get(int index){
        return dataBool.getBoolean(index);
    }

    public int size(){
        return dataBool.size();
    }

    public BooleanArrayList getDataBool(){
        return dataBool;
    }

    public BooleanListIterator createIterator(){
        return dataBool.listIterator();
    }

}
