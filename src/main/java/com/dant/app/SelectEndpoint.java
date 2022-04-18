package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.Utils;
import com.dant.storage.BasicStorage;
import org.javatuples.Triplet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/api/select")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
public class SelectEndpoint {

    @GET
    @Path("/{table}/{columns}")
    public String select(@PathParam("table") String tableParam,@PathParam("columns") String columns) throws IOException {
        return BasicStorage.select(tableParam,columns);
    }

    @GET
    @Path("/exception")
    public Response exception() {
        throw new RuntimeException("Mon erreur");
    }

}
