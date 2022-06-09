package com.dant.app;

import com.dant.entity.Database;
import com.dant.entity.Table;
import com.dant.entity.CSVLoading;
import com.dant.entity.distribution.DistributionManage;
import org.javatuples.Triplet;

import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;

@Path("/api/insert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({"text/csv","text/plain"})
public class InsertEndpoint {

    @PUT
    @Path("/{db}/{table}")
    // Insertion d'une table
    public String insertTable(@PathParam("db") String nameDB, @PathParam("table") String name_table,
                              @QueryParam("limit") int limit, @QueryParam("distributed") boolean distrib,
                              @QueryParam("paquets") int alternate, InputStream in) throws IOException {
        //if(BasicStorage.getDb().getName() != nameDB){ return null; }
        if(distrib){
            return DistributionManage.insertTableDistribution(nameDB ,name_table, limit, in, alternate);
        }else{
            return CSVLoading.insertTable(name_table, limit, in);
        }
    }

}
