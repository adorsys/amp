package de.adorsys.amp.server.test.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("version")
public class VersionResource {
	
	@GET
	public String hello() {
		return "hello";
	}

}
