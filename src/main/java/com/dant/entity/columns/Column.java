package com.dant.entity.columns;

import java.util.Arrays;
import java.util.List;


public class Column{
    protected String name;
    protected String type;

    public Column(String name, String type){
        this.name=name;
        this.type=type;
    }

    public String getName() { return name; }
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addElement(String data) {
        switch (this.type) {
            case "String":
                ColumnString colString = (ColumnString) this;
                colString.add(data);
                return;
            case "float":
                ColumnFloat colFloat = (ColumnFloat) this;
                colFloat.add(Float.parseFloat(data));
                break;
            case "int":
                ColumnInt colInt = (ColumnInt) this;
                if(data!="")
                    colInt.add(Integer.parseInt(data));
                else
                    colInt.add(-1);
                break;
            case "byte":
                ColumnByte colByte = (ColumnByte) this;
                colByte.add(Byte.parseByte(data));
                break;
            case "double":
                ColumnDouble colDouble = (ColumnDouble) this;
                colDouble.add(Double.parseDouble(data));
                break;
            case "short":
                ColumnShort colShort = (ColumnShort) this;
                colShort.add(Short.parseShort(data));
                break;
            case "long":
                ColumnLong colLong = (ColumnLong) this;
                colLong.add(Long.parseLong(data));
                break;
            case "boolean":
                ColumnBoolean colBoolean = (ColumnBoolean) this;
                colBoolean.add(Boolean.parseBoolean(data));
                break;
            default:
                System.out.println("Type non pris en charge.");
                break;
        }
    }
    public String getElement(int line){
        switch(this.type){
            case "float":
                ColumnFloat colFloat = (ColumnFloat) this;
                return String.valueOf(colFloat.get(line));
            case "String":
                ColumnString colString = (ColumnString) this;
                return colString.get(line);
            case "byte":
                ColumnByte colByte = (ColumnByte) this;
                return String.valueOf(colByte.get(line));
            case "int":
                ColumnInt colInt = (ColumnInt) this;
                return String.valueOf(colInt.get(line));
            case "double":
                ColumnDouble colDouble = (ColumnDouble) this;
                return String.valueOf(colDouble.get(line));
            case "short":
                ColumnShort colShort = (ColumnShort) this;
                return String.valueOf(colShort.get(line));
            case "long":
                ColumnLong colLong = (ColumnLong) this;
                return String.valueOf(colLong.get(line));
            case "boolean":
                ColumnBoolean colBoolean = (ColumnBoolean) this;
                return String.valueOf(colBoolean.get(line));
            default:
                System.out.println("Type non pris en charge.");
                return null;
        }
    }
    public static int getColumnNumber(String column_name, String firstLine){
        List<String> head = Arrays.asList(firstLine.split(","));
        return head.indexOf(column_name);

    }
}
