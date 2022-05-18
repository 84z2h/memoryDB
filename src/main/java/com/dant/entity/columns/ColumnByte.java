package com.dant.entity.columns;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.bytes.ByteListIterator;


public class ColumnByte extends Column{

    private ByteArrayList dataByte = new ByteArrayList();

    public ColumnByte(String name){
        super(name, "byte");
    }

    public void add(byte bt){
        dataByte.add(bt);
    }

    public byte get(int index){
        return dataByte.getByte(index);
    }

    public int size(){
        return dataByte.size();
    }

    public ByteArrayList getDataByte(){
        return dataByte;
    }

    public ByteListIterator createIterator(){
        return dataByte.listIterator();
    }

}
