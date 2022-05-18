package com.dant.entity.columns;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntListIterator;


public class ColumnInt extends Column{

    private IntArrayList dataInt = new IntArrayList();

    public ColumnInt(String name){
        super(name, "int");
    }

    public void add(int i){
        dataInt.add(i);
    }

    public int get(int index){
        return dataInt.getInt(index);
    }

    public int size(){
        return dataInt.size();
    }

    public IntArrayList getDataInt(){
        return dataInt;
    }

    public IntListIterator createIterator(){
        return dataInt.listIterator();
    }

}
