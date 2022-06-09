package com.dant.entity.columns;

import com.dant.entity.Table;
import com.dant.filter.GsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.jupiter.api.Test;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {
    @Test
    public void testAddElement()
    {
        Table table = new Table("table");
        Column column = new Column("testInt", "int");
        String data = "3";

        table.addColumnToColumns("test", column);

        column.addElement(data);

        HashMap<String, Column> columns = table.getColumns();

        assertTrue(columns.get("test") instanceof ColumnInt);
    }

}