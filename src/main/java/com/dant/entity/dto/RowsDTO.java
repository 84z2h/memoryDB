package com.dant.entity.dto;

import java.io.Serializable;
import java.util.List;

public class RowsDTO implements Serializable {
    private List<String> columns;
    private List<String> lines;

    public RowsDTO(List<String> columns, List<String> lines) {
        this.columns = columns;
        this.lines = lines;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<String> getLines() {
        return lines;
    }
}
