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

	public List<UDDISLA> getServiceByName(String serviceName);

	public List getDummy(String serviceName, short mttr, short mtbf,
			short latency, short responseTime, short availability);

	public void setClazz(Class<T> clazzToSet);
}
