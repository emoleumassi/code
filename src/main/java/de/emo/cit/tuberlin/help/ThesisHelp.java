package de.emo.cit.tuberlin.help;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ThesisHelp {

	private final static UUID createUUID() {
		return UUID.randomUUID();
	}

	public static final String newUUID() {
		return String.valueOf(createUUID());
	}

	public static final String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static final Response currentResponse(Status code, String message) {
		return Response.status(code).entity(message)
				.header("Date", ThesisHelp.currentDate())
				.header("Server", "Apache Tomcat/8.0.20")
				.header("Content-Type", MediaType.APPLICATION_JSON)
				.header("Connection", "Opened").type(MediaType.TEXT_PLAIN)
				.build();
	}
}
