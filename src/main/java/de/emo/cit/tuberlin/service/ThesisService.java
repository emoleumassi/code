package de.emo.cit.tuberlin.service;

import java.util.List;

public interface ThesisService<T> {

	public void setClazz(final Class<T> clazzToSet);
	
	public List<T> listEntity();

	public T getEntityById(String id);

	public void createEntity(T t);
	
	public void updateColumnById(String field, String tableId, String targetId, String id);
}
