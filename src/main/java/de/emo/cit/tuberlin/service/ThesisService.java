package de.emo.cit.tuberlin.service;

import java.util.List;
/**
 * 
 * @author emoleumassi
 *
 * @param <T>
 */
public interface ThesisService<T> {

	public void setClazz(final Class<T> clazzToSet);
	
	public List<T> listEntity();

	public T getEntityById(Class<T> clazzToSet, String id);

	public void createEntity(T t);
	
	public void updateColumnById(String table, String field, String tableId, String targetId, String id);
}
