package com.dant.app;

import com.dant.entity.ResultSet;
import com.dant.storage.BasicStorage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/api/select")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN, MediaType.MULTIPART_FORM_DATA})
public class SelectEndpoint {
/*
    @GET
    @Path("/{table}/{columns}")
    public String select(@PathParam("table") String tableParam,@PathParam("columns") String columns) throws IOException {
        return BasicStorage.select(tableParam, columns);
    }
*/

    @GET
    @Path("/{table}/{columns}")
    public ResultSet selectJson(@PathParam("table") String tableParam, @PathParam("columns") String columns) throws IOException {
        return BasicStorage.selectJson(tableParam, columns);
    }
/*
    @GET
    @Path("/{table}/{columns}/{whereclause}")
    public String where(@PathParam("table") String table, @PathParam("columns") String columns, @PathParam("whereclause") String whereclause){
        return BasicStorage.select_where(table, columns,whereclause);
    }
*/
    @GET
    @Path("/{table}/{columns}/{whereclause}")
    public ResultSet whereJson(@PathParam("table") String table, @PathParam("columns") String columns, @PathParam("whereclause") String whereclause){
        return BasicStorage.select_whereJson(table, columns,whereclause);
    }

    @GET
    @Path("/exception")
    public Response exception() {
        throw new RuntimeException("Mon erreur");
    }

}
