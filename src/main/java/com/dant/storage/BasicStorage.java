package com.dant.storage;

import com.dant.entity.*;
import com.dant.entity.columns.Column;

import java.util.*;

public class BasicStorage {
    private static Database db = new Database("memoryDB");

    /* === Database Storage === */
    public static Database getDb() {return db;}
    public static void setDb(Database db) {BasicStorage.db = db;}
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
    public static ResultSet select(String table_name, String columnstab){
        System.out.println("start select data");
        TimerManage.start();
        ResultSet result = new ResultSet();
        String[] line;
        Table t = BasicStorage.getTable(table_name);
        if(columnstab.equals("*")){ //SELECT ALL
            int j;
            for(int i = 0; i < t.getSize();i++) {
                line = new String[t.getColumns().keySet().size()];
                j=0;
                for (String key: t.getColumns().keySet()) {
                    line[j] = t.getColumns().get(key).getElement(i);
                    j++;
                }
                result.addString(line);
            }
        }else {
            String[] split = columnstab.split(",");
            int j;
            for (int i = 0; i < t.getSize(); i++) {
                line = new String[split.length];
                for (j = 0; j < split.length; j++) {
                    line[j] = t.getColumns().get(split[j]).getElement(i);
                }
                result.addString(line);
            }
        }
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end select data");
        return result;
    }

    public static ResultSet select_where(String table_name, String columns, String whereclause){
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
                    String[] cond = subclause.split("=");
                    String value = t.getColumns().get(cond[0]).getElement((int)i);
                    if(value.equals(cond[1])){
                        cpt++;
                    }
                }
                if(cpt==and.length){
                    String[] cols = columns.split(",");
                    String[] line = new String[cols.length];
                    int k=0;
                    for(String col: cols) {
                        line[k] = t.getColumns().get(col).getElement((int)i);
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

    public static ResultSet select_where_groupby(String table_name, String columns, String whereclause,String groupby,boolean count){
        System.out.println("start select where groupby data");
        TimerManage.start();
        String[] groupby_columns = groupby.split(",");
        String s = "";
        for(String col : groupby_columns){
            if(!columns.contains(col))
                s += ","+col;
        }
        ResultSet result_where_groupby = select_where(table_name,columns+s,whereclause);
        ResultSet result_where = select_where(table_name,columns,whereclause);
        List<String> cols = Arrays.asList((columns+s).split(","));//Ordre des colones pour obtenir l'index
        List<String> groups = new ArrayList<>();

        List<Integer> counter = new ArrayList<>();//pas utilisé si count = false

        ResultSet result = new ResultSet();
        for(int i = 0;i<result_where_groupby.getSize();i++){
            String[] list = result_where_groupby.getLine(i);
            String group = "";
            for(String groupby_column: groupby_columns){
                group += list[cols.indexOf(groupby_column)];
            }
            if(!groups.contains(group)){
                groups.add(group);
                if(count)
                    counter.add(1);
                result.addString(result_where.getLine(i));
            }
            else{
                if(count) {
                    counter.set(groups.indexOf(group), counter.get(groups.indexOf(group))+1 );
                }
            }
        }
        if(count){
            for(int i = 0;i<result.getSize();i++){
                result.addElementToLine(i,counter.get(i).toString());
            }
        }
        TimerManage.pause();
        System.out.println("Time : " + TimerManage.getTime()+" ms");
        System.out.println("end select groupby data");
        return result;
    }
    /* === SQLRequest === */

}
