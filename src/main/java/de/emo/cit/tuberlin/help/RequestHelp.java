package de.emo.cit.tuberlin.help;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RequestHelp {

	public static final Status BAD_REQUEST_STATUS = Response.Status.BAD_REQUEST;
	public static String BAD_REQUEST_MESSAGE = "HTTP/1.1 "
			+ BAD_REQUEST_STATUS.getStatusCode() + " Bad Request";
	
	public static final Status OK = Response.Status.OK;
}
