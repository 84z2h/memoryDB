package com.dant.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private static ArrayList<Table> tables;

    public Database(String name){
            this.name=name;
            this.tables= new ArrayList<Table>();
    }
    public void addTable(String table) throws IOException {
        Table new_table=Utils.loadTableFromData(table);
        this.tables.add(new_table);
    }
}
