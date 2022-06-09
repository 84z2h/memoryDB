package com.dant.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private String name;
    private final HashMap<String, Table> tables = new HashMap<>();

    public Database(String name) {this.name = name;}

    // Getter Tables
    public HashMap<String,Table> getTables() { return tables; }

    // Getter Name
    public String getName() { return name; }

    // Setter Name
    public void setName(String name) { this.name = name; }

    @Override
    // To String Database
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", tables=" + tables +
                '}';
    }
}
