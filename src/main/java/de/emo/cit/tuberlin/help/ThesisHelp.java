package de.emo.cit.tuberlin.help;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
			ResponseHelp.BAD_REQUEST_MESSAGE += "Check the " + element
					+ " please!.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}

	}

	/**
	 * Generate the current datetime
	 * 
	 * @return
	 */
	public static final String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
