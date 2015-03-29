package de.emo.cit.tuberlin.help;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author emoleumassi
 * 
 */
public class ResponseHelp {

	public static final Status BAD_REQUEST_STATUS = Response.Status.BAD_REQUEST;
	public static String BAD_REQUEST_MESSAGE = "HTTP/1.1 "
			+ BAD_REQUEST_STATUS.getStatusCode() + " Bad Request. ";

	public static final Status OK = Response.Status.OK;

	/**
	 * Create a response for the HTTP-Request.
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static final Response currentResponse(Status code, Object message) {
		return Response.status(code).entity(message)
				.header("Date", ThesisHelp.currentDate())
				.header("Server", "Apache Tomcat/8.0.20")
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.header("Connection", "Opened")
				.type(MediaType.APPLICATION_JSON).build();
	}
}
