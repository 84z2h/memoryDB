package com.dant.app;

import com.dant.entity.Table;
import com.dant.entity.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;


@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SelectEndpoint {
//	private Table table;

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorld() throws IOException {
		Table table = Utils.loadTableFromData("yellow_tripdata_2009-01");
		return table.getBasicStorage().toString();
	}

	@GET
	@Path("/vendorname")
	public String SelectVendorName() throws IOException {
		Table table = Utils.loadTableFromData("yellow_tripdata_2009-01");
		return Arrays.deepToString(table.getBasicStorage().select("vendor_name"));
	}

	@GET
	@Path("/passengercount")
	public String SelectPassagerCount() throws IOException {
		Table table = Utils.loadTableFromData("yellow_tripdata_2009-01");
		return Arrays.deepToString(table.getBasicStorage().select("Passenger_Count"));
	}


	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

}
