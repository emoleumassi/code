package de.emo.cit.tuberlin.service;

import java.util.List;

import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 * 
 */
public interface GetService<T> {

	@SuppressWarnings("rawtypes")
	public List getAllEntities();

	public List<UDDISLA> getUDDISLAByIdName(String idname);

	public T getUddiOrSla(String uddislaId);

	public T getTerms(String uddislaId, String termId, String col);

	public void setClazz(Class<T> clazzToSet);
}
