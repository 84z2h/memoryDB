package com.dant.entity;

import com.dant.storage.BasicStorage;

public class Table {
    private static Column [] columns;
    private String name;
    private BasicStorage basicStorage;

    public Table(Column[] columns, String name, BasicStorage basicStorage) {
        this.columns = columns;
        this.name = name;
        this.basicStorage = basicStorage;
    }

    public Table(Column[] columns, String name) {
        this.columns = columns;
        this.name = name;
    }
    public static int getColumnNumber(String columnname){
        for(int i = 0;i < columns.length;i++){
            if(columns[i].getName().equals(columnname)){
                return i;
            }
        }
        return 0;
    }
    public static Column[] getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public BasicStorage getBasicStorage() {
        return basicStorage;
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStorage(BasicStorage basicStorage) {
        this.basicStorage = basicStorage;
    }
}
