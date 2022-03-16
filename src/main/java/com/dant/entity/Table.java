package com.dant.entity;

public class Table {
    private Column [] columns;
    private String name;
    private Storage storage;

    public Table(Column[] columns, String name, Storage storage) {
        this.columns = columns;
        this.name = name;
        this.storage = storage;
    }

    public Column[] getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
