package com.dant.entity;

import com.dant.entity.columns.Column;

import java.util.*;

public class Table{
    private String name;
    private final HashMap<String, Column> columns = new HashMap<>();
    private long size;

    public Table(String name){
        this.name = name;
        size=0;
    }

    // Getter Name
    public String getName() { return name; }

    //Setter Name
    public void setName(String name) { this.name = name; }

    //Getter Name
    public HashMap<String, Column> getColumns() { return columns; }

    //Getter Size
    public long getSize() {return size;}

    //Setter Size
    public void setSize(long size) {this.size = size;}

    //For testing purposes
    public void addColumnToColumns(String key, Column column){
        this.columns.put(key, column);
    }

    @Override
    //ToString Table
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
