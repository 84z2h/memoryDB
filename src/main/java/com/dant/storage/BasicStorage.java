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
        Table t = BasicStorage.getTable(table_name);
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
        return res.toString();
    }

    public static String select_where(String table_name, String columns, String whereclause){
        //path/table/column,column2/column<VTS&&vendor_name=value2
        System.out.println("start select where data");
        Utils.start();
        Table t= BasicStorage.getTable(table_name);
        StringBuilder res = new StringBuilder();
        String[] split = whereclause.split("@@");
        // tableau de "column"
        for(long i =0;i<t.getSize();i++){
            boolean clause = false;
            int j = 0;
            while(clause==false && j<split.length){
                String[] and = split[j].split("&&");
                int cpt=0;
                for(String subclause : and){
                    if(subclause.contains("=")){
                        String[] cond = subclause.split("=");
                        Object value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i);
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains("<")){
                        String[] cond = subclause.split("<");
                        Object value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i);
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains(">")){
                        String[] cond = subclause.split(">");
                        Object value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i);
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains("<=")){
                        String[] cond = subclause.split("<=");
                        Object value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i);
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains(">=")){
                        String[] cond = subclause.split(">=");
                        Object value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i);
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else{
                        System.out.println("Erreur de syntaxe !");
                        return "ERROR";
                    }
                }
                if(cpt==and.length){
                    for(String col: columns.split(",")) {
                        res.append(BasicStorage.getColumn(table_name, col).getData().get((int) i));
                    }
                    clause = true;
                }
                j++;
            }
        }
        Utils.pause();
        System.out.println("Time : " + Utils.getTime()+" ms");
        System.out.println("end select data");
        return res.toString();
    }
    /* === SQLRequest === */

}
