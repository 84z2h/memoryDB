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
    @GET
    @Path("/{table}/{columns}")
    // Requete Select, prend en parametre une list de columns à selectionner
    public ResultSet select(@PathParam("table") String tableParam, @PathParam("columns") String columns) throws IOException {
        return BasicStorage.select(tableParam, columns);
    }

    @GET
    @Path("/{table}/{columns}/{whereclause}")
    // Requete select where, prend en parametre une liste de colomnes et une list de conditions
    public ResultSet where(@PathParam("table") String table, @PathParam("columns") String columns, @PathParam("whereclause") String whereclause){
        return BasicStorage.select_where(table, columns,whereclause);
    }

    @GET
    @Path("/{table}/{columns}/{whereclause}/{groupby}")
    // Requete select where groupby, prend en parametre une liste de colomnes, une liste de conditions ainsi qu'une list que la colomner à regrouper
    public ResultSet groupby(@PathParam("table") String table, @PathParam("columns") String columns, @PathParam("whereclause") String whereclause,@PathParam("groupby") String groupby){
        return BasicStorage.select_where_groupby(table, columns,whereclause,groupby);
    }

    @GET
    @Path("/exception")
    public Response exception() {
        throw new RuntimeException("Mon erreur");
    }
}
