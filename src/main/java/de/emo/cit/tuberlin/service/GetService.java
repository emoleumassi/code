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
	
	public T getUDDI(String uddislaId);

	public void setClazz(Class<T> clazzToSet);
}
