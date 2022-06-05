package com.dant.entity.dto;

import com.dant.entity.Table;

import java.io.Serializable;
import java.util.List;

public class TableDTO implements Serializable {

    private String name;
    private List<ColumnDTO> columns;

    public TableDTO(String name, List<ColumnDTO> columns){
        this.name = name;
        this.columns = columns;
    }

    public Table toTable(){
        Table table = new Table(name);
        columns.forEach(col -> {
            table.getColumns().put(col.getName(), col.toColumn());
        });
        return table;
    }

}
