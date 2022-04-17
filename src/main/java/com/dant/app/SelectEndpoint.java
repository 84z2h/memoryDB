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

@Path("/api/select")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
public class SelectEndpoint {
	/*
	private static Database database = null;

	static {
		try {
			database = new Database();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/all/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorld(@PathParam("table") String tableParam) throws IOException {
		Table table = database.getTableFromName(tableParam);
		return table.getBasicStorage().toString();
	}

	@GET
	@Path("/{column}/{table}/{where}")
	@Produces(MediaType.APPLICATION_JSON)
	public String select(@PathParam("column") String column, @PathParam("table") String tableParam, @PathParam("where") String whereParam) throws IOException {
		String [] columns = column.split(",");
		Table table = database.getTableFromName(tableParam);
		String [] where = whereParam.split(",");
		String [][] whereMatrice = new String[where.length][];
		Triplet<String, String, String> [] whereTriplet = new Triplet[whereMatrice.length];

		for(int i = 0; i < whereMatrice.length; i++){
			whereMatrice[i] = where[i].split(";");
		}
		for(int i = 0; i < whereTriplet.length; i++){
			whereTriplet[i] = new Triplet<String, String, String>(whereMatrice[i][0], whereMatrice[i][1], whereMatrice[i][2]);
		}

		for(int i = 0; i < whereTriplet.length; i++){
			System.out.println(whereTriplet[i].toString());
		}

		return Utils.buildStringFromData(table.getBasicStorage().select(columns));
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

 */
}
