package com.dant.app;

import com.dant.entity.Database;
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
	private static Database database = null;

	static {
		try {
			database = new Database();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@GET
//	@Path("/hello")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String helloWorld() throws IOException {
//		return table.getBasicStorage().toString();
//	}

	@GET
	@Path("/select/{column}/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public String select(@PathParam("column") String column, @PathParam("table") String tableParam) throws IOException {
		String [] columns = column.split(",");
		Table table = database.getTableFromName(tableParam);
		return Utils.buildStringFromData(table.getBasicStorage().select(columns));
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

}
