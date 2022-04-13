package com.dant.app;

import com.dant.entity.Table;
import com.dant.entity.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SelectEndpoint {
	private static Table table = null;

	static {
		try {
			table = Utils.loadTableFromData("yellow_tripdata_2009-01");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorld() throws IOException {
		return table.getBasicStorage().toString();
	}

	@GET
	@Path("/select/{column}/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public String select(@PathParam("column") String column, @PathParam("table") String tableParam) throws IOException {

		Table table = Utils.loadTableFromData(tableParam);
		return Utils.buildStringFromData(table.getBasicStorage().select(column));
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

}
