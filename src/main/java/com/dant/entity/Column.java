package com.dant.entity;

import com.dant.storage.BasicStorage;

public class Column {
    private String name;
    private String type;

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

    public void setType(String type) {
        this.type = type;
    }

    public void parseValue(String type){
        // TODO
    }
}
