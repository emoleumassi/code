package de.emo.cit.tuberlin.service;

import java.util.List;

import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 * 
 */
@SuppressWarnings("rawtypes")
public interface GetService<T> {

	public List getAllEntities();

	public List<UDDISLA> getUDDISLAByIdName(String idname);

	public T getUddiOrSla(String uddislaId);

	public List getTerms(String uddislaId, String serviceId);

	public List<UDDISLA> getServices(String serviceName);

	public void setClazz(Class<T> clazzToSet);
}
