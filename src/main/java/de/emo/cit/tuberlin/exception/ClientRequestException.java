package de.emo.cit.tuberlin.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import de.emo.cit.tuberlin.help.ThesisHelp;

/**
 * 
 * @author emoleumassi
 * 
 */
public class ClientRequestException extends WebApplicationException {

	private static final long serialVersionUID = -7239312990764229787L;

	public ClientRequestException(Status code, String message) {

		super(ThesisHelp.currentResponse(code, message));
	}
}
