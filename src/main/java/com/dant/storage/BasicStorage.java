package com.dant.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.dant.entity.Database;
import com.dant.entity.Utils;
import org.javatuples.*;

import com.dant.entity.Column;
import com.dant.entity.Table;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class BasicStorage {
    private static Database db;

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

    /* === SQLRequest === */
    public static String select(String table_name, String columnstab){
        System.out.println("start select data");
        Utils.start();
        StringBuilder res = new StringBuilder();
        Table t= BasicStorage.getTable(table_name);
        if(columnstab.equals("*")){ //SELECT ALL
            for(int i = 0; i < t.getSize();i++) {
                for (String key: t.getColumns().keySet()) {
                    res = res.append(t.getColumns().get(key).getData().get(i)).append(" ");
                }
                res = res.append("\n");
            }
        }else {
            String[] split = columnstab.split(",");
            int j;
            for (int i = 0; i < t.getSize(); i++) {
                for (j = 0; j < split.length; j++) {
                    res = res.append(t.getColumns().get(split[j]).getData().get(i)).append(" ");
                }
                res = res.append("\n");
            }
        }
        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end select data");
        System.out.println(res.toString());
        return res.toString();
    }
/*
    public static String select_where(String table_name, String columnstab,String column, String value){
        String res = BasicStorage.select(table_name, "*");
        ArrayList<String> columnData = BasicStorage.getColumn(table_name, column).getData();
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0;i<columnData.size();i++){
            if(columnData.get(i).equals(value)){
                indexes.add(i);
            }
        }
        StringBuilder newres = new StringBuilder();
        String[] lines = res.split("\n");
        for(int i : indexes){
            newres.append(lines[i]);
        }
        return newres.toString();
    }
    */
    public static String select_where(String table_name, String column, String whereclause){
        //path/table/column,column2/column<VTS&&vendor_name=value2
        System.out.println("start select where data");
        Utils.start();
        Table t= BasicStorage.getTable(table_name);
        StringBuilder res = new StringBuilder();

        ArrayList<String> columnData = BasicStorage.getColumn(table_name, column).getData();
        String[] split = whereclause.split("||"); //erreur possible
        // tableau de "column"


        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end select data");
        System.out.println(res.toString());
        return res.toString();
    }
    /* === SQLRequest === */
}
