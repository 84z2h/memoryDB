package com.dant.entity.columns;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatListIterator;


public class ColumnFloat extends Column{

    private FloatArrayList dataFloat = new FloatArrayList();

    public ColumnFloat(String name){
        super(name, "float");
    }

    public void add(float f){
        String s = String.format("%.2f",f);
        dataFloat.add(Float.valueOf(s.replace(',','.')));
    }

    public float get(int index){
        return dataFloat.getFloat(index);
    }

    public int size(){
        return dataFloat.size();
    }

    public FloatArrayList getDataFloat(){
        return dataFloat;
    }

    public FloatListIterator createIterator(){
        return dataFloat.listIterator();
    }

}
