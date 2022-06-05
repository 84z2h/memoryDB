package com.dant.app;

import com.dant.entity.Table;
import com.dant.entity.dto.ColumnDTO;
import com.dant.entity.dto.TableDTO;
import com.dant.filter.GsonProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateEndpointTest {
    @Test
    public void testCreateDB() throws Exception
    {
        ResteasyProviderFactory rpf = ResteasyProviderFactory.newInstance();
        rpf.registerProvider(GsonProvider.class);
        ResteasyClient client = new ResteasyClientBuilder().withConfig(rpf).build();

        ResteasyWebTarget target = client.target("http://localhost:8080/api/create/memoryDB");

        Table table = new Table("test");

        Response rep = target.request()
                .header("Content-Type", "application/json")
                .post(Entity
                        .entity(table, "application/json"));

        assertEquals(200, rep.getStatus());
    }

    @Test
    public void testCreateDBCsv() throws Exception
    {
        ResteasyProviderFactory rpf = ResteasyProviderFactory.newInstance();
        rpf.registerProvider(GsonProvider.class);
        ResteasyClient client = new ResteasyClientBuilder().withConfig(rpf).build();

        ResteasyWebTarget target = client.target("http://localhost:8080/api/create/memoryDB/yellow_tripdata_2009-01");

        Table table = new Table("test2");

        Response rep = target.request()
                .header("Content-Type", "application/json")
                .post(Entity
                        .entity(table, "application/json"));

        assertEquals(200, rep.getStatus());
    }

    @Test
    public void testCreateDBJson() throws Exception
    {
        ResteasyProviderFactory rpf = ResteasyProviderFactory.newInstance();
        rpf.registerProvider(GsonProvider.class);
        ResteasyClient client = new ResteasyClientBuilder().withConfig(rpf).build();

        ResteasyWebTarget target = client.target("http://localhost:8080/api/create/memoryDB/json");

        List<ColumnDTO> cols = new ArrayList<>();
        ColumnDTO col1 = new ColumnDTO("col", "int");
        cols.add(col1);

        TableDTO table = new TableDTO("test3", cols);

        Response rep = target.request()
                .header("Content-Type", "application/json")
                .post(Entity
                        .entity(table, "application/json"));

        assertEquals(200, rep.getStatus());
    }
}