package com.dant.storage;

import java.util.List;

public interface Storage {

    void insert(String[] line);

    List<String[]> select(/*where*/);
}
