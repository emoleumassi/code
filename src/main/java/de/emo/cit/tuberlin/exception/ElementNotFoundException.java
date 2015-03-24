package de.emo.cit.tuberlin.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.emo.cit.tuberlin.help.ThesisHelp;

/**
 * 
 * @author emoleumassi
 * 
 */
public class ElementNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = -7239312990764229787L;

	static Status code = Response.Status.BAD_REQUEST;

	public ElementNotFoundException(String element) {

		super(ThesisHelp.currentResponse(code, element));
	}
}
