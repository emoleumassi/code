package de.emo.cit.tuberlin.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.service.DeleteService;

/**
 * 
 * @author emoleumassi
 * 
 */
@Transactional
@Service("deleteService")
public class DeleteServiceImpl implements DeleteService {


	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void deleteById(String uddislaId) {

		try {
			String query = "SET FOREIGN_KEY_CHECKS=0";
			entityManager.createQuery(query).executeUpdate();
			query = "DELETE FROM UDDISLA WHERE uddislaId = :uddislaId";
			entityManager.createQuery(query)
					.setParameter("uddislaId", uddislaId).executeUpdate();
			query = "SET FOREIGN_KEY_CHECKS=1";
			entityManager.createQuery(query).executeUpdate();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
	}
}
