package com.dant.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private String name;
    private final HashMap<String, Table> tables = new HashMap<>();

    //private ArrayList<Table> database = new ArrayList<>(); // A ENLEVER

    public Database(String name) {this.name = name;}
    public HashMap<String,Table> getTables() { return tables; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", tables=" + tables +
                '}';
    }

/* A ENLEVER
    public Database(String name, String table) throws IOException {
        this.name=name;
        File dir = new File("src\\main\\resources");
        File[] directoryListing = dir.listFiles();
        if (directoryListing !=null){
            for (File child : directoryListing){
                if( child.getName() == table){
                    database.add(Utils.loadTableFromData(table));
                }
            }
        }
    }

    public Database() throws IOException {
        File dir = new File("src\\main\\resources");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                database.add(Utils.loadTableFromData(child.getName()));
            }
        }
    }

    public ArrayList<Table> getDatabase(){
        return database;
    }

    public Table getTableFromName(String name){
        for(Table t : database){
            if(t.getName().equals(name)){
                System.out.println("Found table");
                return t;
            }
        }
        return null;
    }

    public void setTableFromDatabase(Table table,String name) {
        Table current = getTableFromName(name);
        if(current==null)
            System.out.println("Cette table n'existe pas.");
        else{
            int index = database.indexOf(current);
            database.set(index,table);
        }
    }
     */
}
