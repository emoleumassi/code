package de.emo.cit.tuberlin.help;

import javax.ws.rs.QueryParam;

import de.emo.cit.tuberlin.exception.ElementNotFoundException;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 * 
 */
public class CheckJsonData {

	private ThesisRoot thesisRoot;

	public CheckJsonData(ThesisRoot thesisRoot) {
		this.thesisRoot = thesisRoot;

		UDDISLA uddisla = this.thesisRoot.getUddisla();

		if (uddisla == null)
			throw new ElementNotFoundException("uddisla");

		checkUDDISLAName(uddisla.getName());
	}

	private void checkUDDISLAName(@QueryParam("name") String name) {

		if (name == null || name.isEmpty())
			throw new ElementNotFoundException("name of uddisla");
	}
}
