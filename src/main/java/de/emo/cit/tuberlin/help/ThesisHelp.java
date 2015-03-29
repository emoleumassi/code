package de.emo.cit.tuberlin.help;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.emo.cit.tuberlin.exception.ClientRequestException;

/**
 * 
 * @author emoleumassi
 * 
 */
public class ThesisHelp {

	private final static UUID createUUID() {
		return UUID.randomUUID();
	}

	/**
	 * generate a new UUID as String.
	 * 
	 * @return
	 */
	public static final String newUUID() {
		return String.valueOf(createUUID());
	}

	/**
	 * validate an UUID with regex.
	 * 
	 * @return
	 */
	public static void validateUUID(String uuid, String element) {
		boolean valid = uuid
				.matches("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		if (!valid) {
			RequestHelp.BAD_REQUEST_MESSAGE += "Check the " + element
					+ " please!.\n";
			throw new ClientRequestException(RequestHelp.BAD_REQUEST_STATUS,
					RequestHelp.BAD_REQUEST_MESSAGE);
		}

	}

	/**
	 * Generate the current datetime
	 * 
	 * @return
	 */
	private static final String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Create a response for the HTTP-Request.
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static final Response currentResponse(Status code, String message) {
		return Response.status(code).entity(message)
				.header("Date", currentDate())
				.header("Server", "Apache Tomcat/8.0.20")
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.header("Connection", "Opened").type(MediaType.TEXT_PLAIN)
				.build();
	}
}
