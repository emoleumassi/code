package de.emo.cit.tuberlin.service;

/**
 * 
 * @author emoleumassi
 *
 * @param <T>
 */
public interface ThesisService<T> {

	public void setClazz(final Class<T> clazzToSet);

	public void createEntity(T t);
	
	public void updateColumnById(String table, String field, String tableId, String targetId, String id);
}
