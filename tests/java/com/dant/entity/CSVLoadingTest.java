package com.dant.entity;

import com.dant.entity.columns.Column;
import com.dant.storage.BasicStorage;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

class CSVLoadingTest {

    @Test
    void insertTableTest() throws IOException {
        String csv_file = "yellow_tripdata_2009-01_compressed.csv";
        int limit = 30000;
        InputStream is = new FileInputStream(new File("src/main/resources/yellow_tripdata_2009-01_compressed.csv"));

        BasicStorage.setDb(new Database("test"));
        BasicStorage.setTable(new Table(csv_file));

        CSVLoading.insertTable(csv_file, limit, is);

        Table table = BasicStorage.getTable(csv_file);

        HashMap<String, Column> columns = table.getColumns();

        assertEquals(csv_file, table.getName());

        //assertEquals("vendor_name", columns.get(1));
    }


}