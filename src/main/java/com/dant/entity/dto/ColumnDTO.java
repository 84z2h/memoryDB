package com.dant.entity.dto;

import com.dant.entity.columns.Column;

import java.io.Serializable;

public class ColumnDTO implements Serializable {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public Column toColumn(){
        return new Column(name, type);
    }

}
