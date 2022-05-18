package com.dant.entity.columns;

import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortListIterator;


public class ColumnShort extends Column{

    private ShortArrayList dataShort = new ShortArrayList();

    public ColumnShort(String name){
        super(name, "short");
    }

    public void add(short sh){
        dataShort.add(sh);
    }

    public short get(int index){
        return dataShort.getShort(index);
    }

    public int size(){
        return dataShort.size();
    }

    public ShortArrayList getDataShort(){
        return dataShort;
    }

    public ShortListIterator createIterator(){
        return dataShort.listIterator();
    }

}
