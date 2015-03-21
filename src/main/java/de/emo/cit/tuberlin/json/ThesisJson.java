package de.emo.cit.tuberlin.json;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author emoleumassi
 *
 */
public class ThesisJson {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThesisJson.class);

	private UDDISLAJson uddislaJson;

	public UDDISLAJson parseJSON() {
		try {
			JAXBContext context = JAXBContext.newInstance(UDDISLAJson.class);
			Unmarshaller um = context.createUnmarshaller();
			um.setProperty("eclipselink.media-type", "application/json");
			um.setProperty("eclipselink.media-type", true);
			InputStream json = readFile("data.json");
			uddislaJson = (UDDISLAJson) um.unmarshal(json);
		} catch (JAXBException e) {
			LOGGER.info(e.getMessage());
		}
		return uddislaJson;
	}

	private InputStream readFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			return classLoader.getResourceAsStream(fileName);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}
