package com.dant.entity.columns;

import it.unimi.dsi.fastutil.chars.CharArrayList;
import it.unimi.dsi.fastutil.chars.CharListIterator;


public class ColumnChar extends Column{

    private CharArrayList dataChar = new CharArrayList();

    public ColumnChar(String name){
        super(name, "char");
    }

    public void add(char c){
        dataChar.add(c);
    }

    public char get(int index){
        return dataChar.getChar(index);
    }

    public int size(){
        return dataChar.size();
    }

    public CharArrayList getDataChar(){
        return dataChar;
    }

    public CharListIterator createIterator(){
        return dataChar.listIterator();
    }

}
