package com.dant.app;

import com.dant.entity.Account;
import com.dant.entity.Table;
import com.dant.entity.Utils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestEndpoint {
//	private Table table;

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorld() throws IOException {

		Table table = Utils.loadTableFromData();
		return table.getBasicStorage().toString();
	}

//	@GET
//	@Path("/vendorname")
////	public String[] getListInParams(@QueryParam("ids") List<String> ids) throws IOException {
////		Table table = Utils.loadTableFromData();
////		return table.getBasicStorage().select("vendor_name");
////	}

	@POST
	@Path("/entity")
	public Account getAccount(Account account) {
		System.out.println("Received account " + account);
		account.setUpdated(System.currentTimeMillis());
		return account;
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

}
