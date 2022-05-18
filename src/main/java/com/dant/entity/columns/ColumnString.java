package com.dant.entity.columns;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;


public class ColumnString extends Column{

    private ObjectArrayList<String> dataString = new ObjectArrayList<>();

    public ColumnString(String name){
        super(name, "String");
    }

    public void add(String s){
        dataString.add(s);
    }

    public String get(int index){
        return dataString.get(index);
    }

    public int size(){
        return dataString.size();
    }

    public ObjectArrayList<String> getDataString(){
        return dataString;
    }

    public ObjectListIterator createIterator(){
        return dataString.listIterator();
    }

}
