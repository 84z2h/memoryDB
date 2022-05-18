package com.dant.entity.columns;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleListIterator;


public class ColumnDouble extends Column{

    private DoubleArrayList dataDouble = new DoubleArrayList();

    public ColumnDouble(String name){
        super(name, "double");
    }

    public void add(double d){
        dataDouble.add(d);
    }

    public double get(int index){
        return dataDouble.getDouble(index);
    }

    public int size(){
        return dataDouble.size();
    }

    public DoubleArrayList getDataDouble(){
        return dataDouble;
    }

    public DoubleListIterator createIterator(){
        return dataDouble.listIterator();
    }

}
