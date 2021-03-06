package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.service.ThesisService;
/**
 * 
 * @author emoleumassi
 *
 * @param <T>
 */
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
		String query = "from " + clazz.getName();
		LOGGER.info(query);
		return entityManager.createQuery(query)
				.getResultList();
	}

	@Override
	public T getEntityById(Class<T> clazzToSet, String id) {
		return entityManager.find(clazzToSet, id);
	}

	@Override
	public void createEntity(T t) {

		try {
			entityManager.persist(t);
			LOGGER.info(t.toString());
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
	}

	@Override
	public void updateColumnById(String table, String field, String tableId,
			String targetId, String id) {
		String query = "UPDATE " + table + " SET " + field + "='" + targetId
				+ "' WHERE " + tableId + "='" + id + "'";
		LOGGER.info(query);
		try {
			entityManager.createQuery(query).executeUpdate();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
	}

}
