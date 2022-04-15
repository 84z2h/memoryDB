package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.Utils;
import org.javatuples.Triplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/create")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateEndpoint {

    @POST
    @Path("/{name}/{table}")
    @Produces(MediaType.APPLICATION_JSON)
    public Database CreateDB(@PathParam("table") String tableParam, @PathParam("name") String nameDB) throws IOException {
        //Database  db = database.getTableFromName(tableParam);
        Database db = new Database(nameDB, tableParam);
        return db;
    }
}
