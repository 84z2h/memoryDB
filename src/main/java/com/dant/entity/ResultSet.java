package com.dant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultSet {
    private List<String[]> data;

    public ResultSet(){
        data= new ArrayList<String[]>();
    }

    @Override
    public String toString() {
        return "ResultSet{" +
                "data=" + data +
                '}';
    }

    // Getter Data
    public List<String[]> getData() {
        return data;
    }
    public void setData(int i,String[] newData) {
        data.set(i, newData);
    }

    public void addElementToLine(int i,String elem) {
        String[] line = data.get(i);
        String[] newline = new String[line.length +1];
        System.arraycopy(line,0,newline,0,line.length);
        newline[line.length] = elem;
        this.setData(i,newline);
    }

    // Getter ième ligne Data
    public String[] getLine(int i){
        return data.get(i);
    }

    //Getter size of Data
    public int getSize(){
        return data.size();
    }

    // Ajout d'un élement à Data
    public void addString(String[] str){
        data.add(str);
    }

    public static void main(String[] args){
        String[] tab = {"un","deux","trois"};
        List<String> tab2 = Arrays.asList(tab);
        tab2.add("quatre");
        String[] tab3 = tab2.toArray(new String[0]);
        for(int i =0;i<tab3.length;i++){
            System.out.println(tab3[i]);

        }
    }

}
