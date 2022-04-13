package com.dant.app;

import com.dant.entity.Table;
import com.dant.entity.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;

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
			table = Utils.loadTableFromData();
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
	@Path("/vendorname")
	public String SelectVendorName() throws IOException {
//		String [] tab = new String[]{"vendor_name"};
		return Utils.buildStringFromData(table.getBasicStorage().select("vendor_name"));
	}

	@GET
	@Path("/passengercount")
	public String SelectPassagerCount() throws IOException {
//		String [] tab = new String[]{"Passenger_Count"};
		return Utils.buildStringFromData(table.getBasicStorage().select("Passenger_Count"));
	}

//	@GET
//	@Path("/vendorname?passengercount")
//	public String SelectColumns() throws IOException {
//		String [] tab = new String[]{"vendor_name","Passenger_Count"};
//		Table table = Utils.loadTableFromData();
//		return Arrays.deepToString(table.getBasicStorage().select(tab));
//	}


	@GET
	@Path("/select/{column}/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public String[][] select(@PathParam("column") String column, @PathParam("table") String tableParam) throws IOException {

		Table table = Utils.loadTableFromData(tableParam);
		return table.getBasicStorage().select(column);
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

}
