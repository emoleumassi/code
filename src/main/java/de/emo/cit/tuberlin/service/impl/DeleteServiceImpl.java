package de.emo.cit.tuberlin.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.service.DeleteService;
import de.emo.cit.tuberlin.service.ThesisService;

/**
 * 
 * @author emoleumassi
 * 
 */
@Transactional
@Service("deleteService")
public class DeleteServiceImpl implements DeleteService {

	@SuppressWarnings("rawtypes")
	@Autowired
	ThesisService thesisService;

	private static String DELETE_FROM = "DELETE FROM ";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public int deleteById(String uddislaId) {

		try {
			UDDI uddi = (UDDI) thesisService.getEntityById(UDDI.class,
					uddislaId);
			String query = DELETE_FROM + "OverviewDoc WHERE uddiId = :uddiId";
			entityManager.createQuery(query)
					.setParameter("uddiId", uddi.getUddiId()).executeUpdate();

			SLA sla = (SLA) thesisService.getEntityById(SLA.class, uddislaId);
			query = DELETE_FROM + "ServiceTerms WHERE sla = :slaId";
			entityManager.createQuery(query)
					.setParameter("slaId", sla.getSlaId()).executeUpdate();

			GuaranteeTerms guaranteeTerms = (GuaranteeTerms) thesisService
					.getEntityById(SLA.class, uddislaId);
			query = DELETE_FROM
					+ "KeyPerformanceIndicator WHERE guaranteeTerms = :slaId";
			entityManager.createQuery(query)
					.setParameter("slaId", sla.getSlaId()).executeUpdate();

			query = DELETE_FROM + "UDDISLA WHERE uddislaId = :uddislaId";
			return entityManager.createQuery(query)
					.setParameter("uddislaId", uddislaId).executeUpdate();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return 0;
	}
}
