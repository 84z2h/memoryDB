package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.Utils;
import org.javatuples.Triplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
@Path("/api/insert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InsertEndpoint {

    @PUT
    @Path("/{db}/{table}")
    @Produces(MediaType.APPLICATION_JSON)
    public Table CreateTable(@PathParam("db") String nameDB, @PathParam("table") String name_table) throws IOException {
        //if(BasicStorage.getDb().getName() != nameDB){ return null; }
        Table t=Utils.loadTable(name_table,1);
        return t;
    }
}
