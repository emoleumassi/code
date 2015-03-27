package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.GetService;

/**
 * 
 * @author emoleumassi
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
@Transactional
public class GetServiceImpl<T> implements GetService {

	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GetServiceImpl.class);

	@Override
	public void setClazz(Class clazzToSet) {
		this.clazz = clazzToSet;
	}

	@Override
	public List getAllEntities() {
		String query = "FROM UDDISLA";
		LOGGER.info(query);
		try {
			return entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UDDISLA> getUDDISLAByIdName(String idName) {

		String query = "FROM UDDISLA WHERE uddislaId = :id OR name = :name";
		LOGGER.info(query);
		try {
			// return entityManager.find(UDDISLA.class, id);
			return (List<UDDISLA>) entityManager.createQuery(query)
					.setParameter("id", idName).setParameter("name", idName)
					.getResultList();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public T getUddiOrSla(String uddislaId) {

		String query = "FROM "
				+ clazz.getName()
				+ " WHERE uddislaId = (SELECT uddislaId FROM UDDISLA WHERE uddislaId = :id)";
		LOGGER.info(query);
		try {
			return (T) entityManager.createQuery(query)
					.setParameter("id", uddislaId).getSingleResult();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public T getTerms(String uddislaId, String termId, String col) {

		String query = "FROM "
				+ clazz.getName()
				+ " WHERE "
				+ col
				+ " = :termId AND slaId = (SELECT slaId FROM SLA WHERE uddislaId = "
				+ "(SELECT uddislaId FROM UDDISLA WHERE uddislaId = :id))";
		LOGGER.info(query);
		try {
			return (T) entityManager.createQuery(query)
					.setParameter("termId", termId)
					.setParameter("id", uddislaId).getSingleResult();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}
