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
@Service
@Transactional
public class GetServiceImpl implements GetService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GetServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("unchecked")
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
}
