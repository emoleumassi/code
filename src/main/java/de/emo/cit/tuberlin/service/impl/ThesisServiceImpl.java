package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.service.ThesisService;

@Transactional
@Service("thesisService")
public class ThesisServiceImpl<T> implements ThesisService<T> {

	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThesisServiceImpl.class);

	public void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listEntity() {
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	@Override
	public T getEntityById(String id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public void createEntity(T t) {

		try {
			entityManager.persist(t);
			LOGGER.info(t.toString());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Override
	public void updateColumnById(String field, String tableId, String targetId, String id) {
		String query = "update SLA " /* + clazz.getName() */+ " set " + field
				+ "=\"" + targetId + "\" where " + tableId + "=\"" + id + "\"";
		LOGGER.info(query);
		try {
			entityManager.createQuery(query).executeUpdate();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

}
