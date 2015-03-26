package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.service.GetService;

/**
 * 
 * @author emoleumassi
 *
 */
@Service
@Transactional
public class GetServiceImpl implements GetService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GetServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("rawtypes")
	public List getAllEntities() {
		String query = "SELECT * FROM UDDISLA, UDDI, OverviewDoc, SLA, ServiceTerms, GuaranteeTerms, KeyPerformanceIndicator";
		LOGGER.info(query);
		try {
			return entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}
