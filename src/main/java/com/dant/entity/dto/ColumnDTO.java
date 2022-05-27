package com.dant.entity.dto;

import com.dant.entity.columns.*;

import java.io.Serializable;

public class ColumnDTO implements Serializable {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public Column toColumn(){
        switch(type){
            case "boolean":
                return new ColumnBoolean(name);
            case "byte":
                return new ColumnByte(name);
            case "char":
                return new ColumnChar(name);
            case "double":
                return new ColumnDouble(name);
            case "float":
                return new ColumnFloat(name);
            case "int":
                return new ColumnInt(name);
            case "long":
                return new ColumnLong(name);
            case "short":
                return new ColumnShort(name);
            case "String":
                return new ColumnString(name);
            default:
                System.out.println("ColumnDTO : type non reconnu !");
                return null;
        }
    }

}
