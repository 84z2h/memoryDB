package com.dant.storage;

import com.dant.entity.*;
import com.dant.entity.columns.Column;

import java.util.Timer;

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
    public static ResultSet selectJson(String table_name, String columnstab){
        System.out.println("start select data");
        TimerManage.start();
        ResultSet result = new ResultSet();
        Table t = BasicStorage.getTable(table_name);
        if(columnstab.equals("*")){ //SELECT ALL
            int j;
            String[] line = new String[t.getColumns().keySet().size()];
            for(int i = 0; i < t.getSize();i++) {
                j=0;
                for (String key: t.getColumns().keySet()) {
                    line[j] = t.getColumns().get(key).getData().get(i).toString();
                    j++;
                }
                result.addString(line);
            }
        }else {
            String[] split = columnstab.split(",");
            int j;
            String[] line = new String[split.length];
            for (int i = 0; i < t.getSize(); i++) {
                for (j = 0; j < split.length; j++) {
                    line[j] = t.getColumns().get(split[j]).getData().get(i).toString();
                }
                result.addString(line);
            }
        }
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end select data");
        return result;
    }

    public static ResultSet select_whereJson(String table_name, String columns, String whereclause){
        System.out.println("start select where data");
        TimerManage.start();
        Table t= BasicStorage.getTable(table_name);
        ResultSet result = new ResultSet();
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
                        String value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i).toString();
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains("<")){
                        String[] cond = subclause.split("<");
                        String value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i).toString();
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains(">")){
                        String[] cond = subclause.split(">");
                        String value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i).toString();
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains("<=")){
                        String[] cond = subclause.split("<=");
                        String value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i).toString();
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                    else if(subclause.contains(">=")){
                        String[] cond = subclause.split(">=");
                        String value = BasicStorage.getColumn(table_name,cond[0]).getData().get((int)i).toString();
                        if(value.equals(cond[1])){
                            cpt++;
                        }
                    }
                }
                if(cpt==and.length){
                    String[] cols = columns.split(",");
                    String[] line = new String[cols.length];
                    int k=0;
                    for(String col: cols) {
                        line[k] = BasicStorage.getColumn(table_name, col).getData().get((int) i).toString();
                        k++;
                    }
                    result.addString(line);
                    clause = true;
                }
                j++;
            }
        }
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end select data");
        return result;
    }
    /* === SQLRequest === */

}
