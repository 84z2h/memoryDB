package com.dant.entity;

import java.util.List;

public interface Storage {

    void insert(String[] line);

    List<String[]> select(/*where*/);
}
