package com.dant.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.dant.entity.*;
import org.javatuples.*;

public class BasicStorage {
    private static Database db;

    // private Database data; A ENLEVER
    //private String[][] data; A ENLEVER

    /* === Database Storage === */
    public static void setDb(Database db) {BasicStorage.db = db;}
    public static Database getDb() {return db;}
    // enleve toutes les tables
    public static void removeDb(){
        db.getTables().clear();
    }
    /* === Database Storage === */

    /* === Table Storage === */
    public static void setTable(Table new_table){
        // evite les doublons
        if (db.getTables().containsKey(new_table.getName()))
            return;
        db.getTables().put(new_table.getName(), new_table);
    }
    // GET PAR OBJET
    public static Table getTable(Table table) {
        if (db==null)
            return null;
        return db.getTables().get(table.getName());
    }
    // GET PAR LE NOM
    public static Table getTable(String name_table) {
        if (db==null)
            return null;
        return db.getTables().get(name_table);
    }
    public static void removeTable(String name_table){
        if (db==null)
            return;
        db.getTables().remove(name_table);
    }
    /* === Table Storage === */

    /* === Column Storage === */
    public static void setColumn(String name_table, Column col){
        // evite les doublons
        if (db.getTables().get(name_table).getColumns().containsKey(col.getName()))
            return;
        db.getTables().get(name_table).getColumns().put(col.getName(), col);
    }
    // GET PAR OBJET
    public static Column getColumn(String name_table, Column col){
        if(db.getTables().get(name_table)== null)
            return null;
        return db.getTables().get(name_table).getColumns().get(col.getName());
    }
    // GET PAR NOM
    public static Column getColumn(String name_table, String name_col){
        if(db.getTables().get(name_table)== null)
            return null;
        return db.getTables().get(name_table).getColumns().get(name_col);
    }
    public static void removeColumn(String name_table, String name_col){
        if(db.getTables().get(name_table)==null)
            return;
        db.getTables().get(name_table).getColumns().remove(name_col);
    }
    /* === Column Storage === */
/*
    public BasicStorage(String[][] data) {
        this.data = data;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                res = res.append(data[i][j]).append(" ");
            }
            res = res.append("\n");
        }
        return res.toString();
    }

    public String[][] select(String column_name) {
        String[][] newdata = new String[10000][1];
        Column[] columns = Table.getColumns();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].getName().equals(column_name)) {
                System.out.println("COLUMN MATCH");
                for (int j = 0; j < data.length; j++) {
                    newdata[j][0] = data[j][i];
//                    System.out.println(data[j][i]);
                }
            }
        }
        return newdata;
    }

    public String[][] select(String[] columnstab) {
        String[][] newdata = new String[100000][columnstab.length];
        Column[] columns = Table.getColumns();
        for (int i = 0; i < columnstab.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (columns[j].getName().equals(columnstab[i])) {
                    System.out.println("COLUMN MATCH WITH " + columns[j].getName());
                    for (int d = 0; d < data.length; d++) {
                        newdata[d][i] = data[d][j];
                    }
                }
            }
        }
        return newdata;
    }

    public String[][] selecttest(String[] columnstab) {
        ArrayList<Triplet<String,String,String>> wherelist = new ArrayList<Triplet<String,String,String>>();
        Triplet<String, String, String> where1 = new Triplet<>("Passenger_count", "=", "1");
        wherelist.add(where1);
        String[][] newdata = new String[100][columnstab.length];
        Column[] columns = Table.getColumns();
        boolean to_write = true;
        for (int i = 0; i < columnstab.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (columns[j].getName().equals(columnstab[i])) {
                    System.out.println("COLUMN MATCH WITH " + columns[j].getName());
                    for (int d = 0; d < data.length; d++) {
                        for(Triplet<String,String,String> where : wherelist){
                            to_write = true;
                            if( !(data[d][3]).equals(where.getValue2())){
                                System.out.println("False");
                                System.out.println(data[d][3]);
                                to_write = false;
                            }
                        }
                        if(to_write)
                            System.out.println("True");
                            System.out.println(data[d][3]);
                            newdata[d][i] = data[d][j];
                    }
                }
            }
        }
        return newdata;
    }
 */
}
