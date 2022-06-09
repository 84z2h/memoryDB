package com.dant.storage;

import com.dant.entity.CSVLoading;
import com.dant.entity.Database;
import com.dant.entity.ResultSet;
import com.dant.entity.Table;
import com.dant.entity.columns.Column;
import com.dant.entity.dto.ColumnDTO;
import com.dant.entity.dto.TableDTO;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicStorageTest {

    @Test
    void SelectTest() throws IOException {
        String csv_file = "ghtorrent-2019-01-07_compressed.csv";
        int limit = 5000;

        InputStream is = new FileInputStream(new File("src/main/resources/ghtorrent-2019-01-07_compressed.csv"));

        BasicStorage.setDb(new Database("test"));

        List<ColumnDTO> columnsDTO = new ArrayList<>();
        ColumnDTO vendor_name = new ColumnDTO("actor_login", "String");
        columnsDTO.add(vendor_name);

        TableDTO tableDTO = new TableDTO(csv_file, columnsDTO);

        Table table = tableDTO.toTable();

        BasicStorage.setTable(table);

        CSVLoading.insertTable(csv_file, limit, is);

        InputStream isNew = new FileInputStream(new File("src/main/resources/ghtorrent-2019-01-07_compressed.csv"));
        BufferedReader in = new BufferedReader(new InputStreamReader(isNew));
        String firstLine = in.readLine();
        in.readLine();
        String line = in.readLine();

        String columnstab = "actor_login";

        String[] split = line.split(",");
        int columnIndex = Column.getColumnNumber(columnstab, firstLine);
        String firstLineVendorName = split[columnIndex];
        ResultSet result = BasicStorage.select(csv_file, columnstab);

        assertEquals(csv_file, table.getName());

        assertEquals(limit, result.getSize());

        assertEquals(firstLineVendorName, result.getLine(0)[0]);
    }


    @Test
    void SelectWhereTest() throws IOException {
        String csv_file = "ghtorrent-2019-01-07_compressed.csv";
        int limit = 5000;

        InputStream is = new FileInputStream(new File("src/main/resources/ghtorrent-2019-01-07_compressed.csv"));

        BasicStorage.setDb(new Database("test"));

        List<ColumnDTO> columnsDTO = new ArrayList<>();
        ColumnDTO actor_login = new ColumnDTO("actor_login", "String");
        columnsDTO.add(actor_login);
        ColumnDTO language = new ColumnDTO("language", "String");
        columnsDTO.add(language);

        TableDTO tableDTO = new TableDTO(csv_file, columnsDTO);

        Table table = tableDTO.toTable();

        BasicStorage.setTable(table);

        CSVLoading.insertTable(csv_file, limit, is);

        InputStream isNew = new FileInputStream(new File("src/main/resources/ghtorrent-2019-01-07_compressed.csv"));
        BufferedReader in = new BufferedReader(new InputStreamReader(isNew));
        String firstLine = in.readLine();
        in.readLine();
        String line = in.readLine();

        String columnstab = "actor_login";

        String[] split = line.split(",");
        int columnIndex = Column.getColumnNumber(columnstab, firstLine);
        String firstLineVendorName = split[columnIndex];
        String where = "language=Java";
        ResultSet result = BasicStorage.select_where(csv_file, columnstab, where);

        assertEquals(csv_file, table.getName());

        //assertEquals(limit, result.getSize());

        assertEquals("caalador", result.getLine(0)[0]);
    }

    @Test
    void SelectWhereGroupByTest() throws IOException {
        String csv_file = "ghtorrent-2019-01-07_compressed.csv";
        int limit = 20000;

        InputStream is = new FileInputStream(new File("src/main/resources/ghtorrent-2019-01-07_compressed.csv"));

        BasicStorage.setDb(new Database("test"));

        List<ColumnDTO> columnsDTO = new ArrayList<>();
        ColumnDTO actor_login = new ColumnDTO("actor_login", "String");
        columnsDTO.add(actor_login);
        ColumnDTO language = new ColumnDTO("language", "String");
        columnsDTO.add(language);
        ColumnDTO author_login = new ColumnDTO("author_login", "String");
        columnsDTO.add(author_login);

        TableDTO tableDTO = new TableDTO(csv_file, columnsDTO);

        Table table = tableDTO.toTable();

        BasicStorage.setTable(table);

        CSVLoading.insertTable(csv_file, limit, is);


        String columnstab = "author_login";
        String where = "language=PHP";
        ResultSet result = BasicStorage.select_where_groupby(csv_file, columnstab, where, "author_login", true);

        assertEquals(csv_file, table.getName());

        assertEquals("joshfeck", result.getLine(0)[0]);

        assertEquals("8", result.getLine(0)[1]);
    }




}