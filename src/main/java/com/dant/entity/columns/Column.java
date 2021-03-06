package com.dant.entity.columns;

import java.util.Arrays;
import java.util.List;


public class Column{
    /* Classe pour une colonne de la table, avec son nom et son type */
    protected String name;
    protected String type;

    public Column(String name, String type){
        this.name=name;
        this.type=type;
    }

    // Get Name
    public String getName() { return name; }

    // Getter Type
    public String getType() {
        return type;
    }

    // Setter Name
    public void setName(String name) {
        this.name = name;
    }

    // Gère l'ajout d'un élement dans une colonne pour tout les types de colonnes existant
    public void addElement(String data) {
        switch (this.type) {
            case "String":
                ColumnString colString = (ColumnString) this;
                colString.add(data);
                return;
            case "int":
                ColumnInt colInt = (ColumnInt) this;
                if(data!="")
                    colInt.add(Integer.parseInt(data));
                else
                    colInt.add(-1);
                break;
            case "float":
                ColumnFloat colFloat = (ColumnFloat) this;
                colFloat.add(Float.parseFloat(data));
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

    // Retourne la ieme ligne d'une colonne selon son type
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

    // Retourne le numéro de la colonne souhaité
    public static int getColumnNumber(String column_name, String firstLine){
        List<String> head = Arrays.asList(firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
        return head.indexOf(column_name);

    }
}
