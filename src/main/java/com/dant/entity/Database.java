package com.dant.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    private ArrayList<Table> database = new ArrayList<>();

    public Database() throws IOException {
        File dir = new File("src\\main\\resources");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                database.add(Utils.loadTableFromData(child.getName()));

            }
        }

    }

    public String toString(){
        String s = "";
        for(Table t : database){
            s+= " "+t.getName().strip();
        }
        return s;
    }

    public ArrayList<Table> getDatabase(){
        return database;
    }

    public Table getTableFromName(String name){
        for(Table t : database){
            if(t.getName()==name){
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
}
