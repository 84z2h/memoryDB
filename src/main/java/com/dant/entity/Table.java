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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HashMap<String, Column> getColumns() { return columns; }
    public long getSize() {return size;}
    public void setSize(long size) {this.size = size;}

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
