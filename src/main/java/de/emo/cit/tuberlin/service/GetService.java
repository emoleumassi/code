package de.emo.cit.tuberlin.service;

import java.util.List;

import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 *
 */
public interface GetService {

	@SuppressWarnings("rawtypes")
	public List getAllEntities();
	
	public List<UDDISLA> getUDDISLAByIdName(String idname);
}
