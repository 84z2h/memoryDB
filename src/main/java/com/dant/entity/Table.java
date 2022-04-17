package com.dant.entity;

import com.dant.storage.BasicStorage;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Table {
    private String name;
    private final HashMap<String, Column> columns = new HashMap<>();
    // lines : <rowId, <columns_name, value>>
    private final HashMap<Long, Map.Entry<String, Object>> lines = new HashMap<>();
    private HashMap<String, Object> cur_values = new HashMap<>();

    //private BasicStorage basicStorage; // A ENLEVER
    //private static Column [] columns_bis; // A ENLEVER

    public Table(String name){ this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HashMap<String, Object> getValues(){ return cur_values;}
    public HashMap<String, Column> getColumns() { return columns; }
    public HashMap<Long, Map.Entry<String, Object>> getLines() { return lines; }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", lines=" + lines +
                '}';
    }
    /*
    public Table(Column[] columns, String name, BasicStorage basicStorage) {
        this.columns_bis = columns;
        this.name = name;
        this.basicStorage = basicStorage;
    }
    public Table(Column[] columns, String name) {
        this.columns_bis = columns;
        this.name = name;
    }
    public static int getColumnNumber(String columnname){
        for(int i = 0;i < columns_bis.length;i++){
            if(columns_bis[i].getName().equals(columnname)){
                return i;
            }
        }
        return 0;
    }
    public static Column[] getColumns() {
        return columns_bis;
    }

    public BasicStorage getBasicStorage() {
        return basicStorage;
    }

    public void setColumns(Column[] columns) {
        this.columns_bis = columns;
    }

    public void setStorage(BasicStorage basicStorage) {
        this.basicStorage = basicStorage;
    }
    */
}
