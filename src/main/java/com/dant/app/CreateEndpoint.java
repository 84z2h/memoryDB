package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.CSVLoading;
import com.dant.entity.distribution.ServiceClient;
import com.dant.entity.dto.TableDTO;
import com.dant.storage.BasicStorage;
import org.javatuples.Triplet;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.security.Provider;

@Path("/api/create")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateEndpoint {
/*
    @POST
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    // Gère de la création d'une base de donnée
    public Database createDB(@PathParam("name") String nameDB) throws IOException {
        Database db = new Database(nameDB);
        BasicStorage.setDb(db);
        return db;
    }
*/
    @POST
    @Path("/{db}/{table}")
    @Produces(MediaType.APPLICATION_JSON)
    // Création d'une table pour une db donné
    public Table createTable(@PathParam("db") String nameDB, @PathParam("table") String name_table) throws IOException {
        Table table =CSVLoading.createTable(name_table);
        return table;
    }

    @POST
    @Path("/{db}/json")
    @Produces(MediaType.APPLICATION_JSON)
    // Création d'une table depuis un JSON pour une db donné
    public TableDTO createTableWithJson(@PathParam("db") String nameDB, TableDTO tableDto,@QueryParam("distributed") Boolean distrib) throws IOException {
        Table table = tableDto.toTable();
        BasicStorage.getDb().getTables().put(table.getName(),table);
        if(distrib){
            MultivaluedMap<String, Object> map = new MultivaluedMapImpl<>();
            map.add("distributed", false);
            ServiceClient.multiPostRequests("/api/create/"+nameDB+"/json",MediaType.APPLICATION_JSON, tableDto, map);
        }
        return tableDto;
    }

}
