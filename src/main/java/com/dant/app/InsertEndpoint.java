package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.CSVLoading;
import org.javatuples.Triplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/api/insert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("text/csv")
public class InsertEndpoint {

    @PUT
    @Path("/{db}/{table}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createTable(@PathParam("db") String nameDB, @PathParam("table") String name_table,@PathParam("limit") int max_size, InputStream in) throws IOException {
        //if(BasicStorage.getDb().getName() != nameDB){ return null; }
        return CSVLoading.insertTable(name_table, max_size, in);
    }
}
