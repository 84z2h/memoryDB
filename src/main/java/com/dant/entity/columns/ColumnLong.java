package com.dant.entity.columns;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongListIterator;


public class ColumnLong extends Column{

    private LongArrayList dataLong = new LongArrayList();

    public ColumnLong(String name){
        super(name, "long");
    }

    public void add(long i){
        dataLong.add(i);
    }

    public long get(int index){
        return dataLong.getLong(index);
    }

    public int size(){
        return dataLong.size();
    }

    public LongArrayList getDataLong(){
        return dataLong;
    }

    public LongListIterator createIterator(){
        return dataLong.listIterator();
    }

}
