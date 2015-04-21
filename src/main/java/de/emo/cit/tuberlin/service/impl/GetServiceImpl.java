package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

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
		try {
			return entityManager.createQuery(query).getResultList();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UDDISLA> getUDDISLAByIdName(String idName) {

		String query = "FROM UDDISLA WHERE uddislaId = :id OR name = :name";
		LOGGER.info(query);
		try {
			return (List<UDDISLA>) entityManager.createQuery(query)
					.setParameter("id", idName).setParameter("name", idName)
					.getResultList();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public T getUddiOrSla(String uddislaId) {

		String query = "FROM " + clazz.getName() + " WHERE uddislaId = :id";
		LOGGER.info(query);
		try {
			return (T) entityManager.createQuery(query)
					.setParameter("id", uddislaId).getSingleResult();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List getTerms(String uddislaId, String serviceId) {

		String query = "FROM ServiceTerms s, GuaranteeTerms g "
				+ "WHERE s.serviceName = g.serviceName AND s.serviceTermId = :serviceId"
				+ " AND s.sla = (SELECT slaId FROM SLA WHERE uddislaId = :id)"
				+ " AND g.sla = (SELECT slaId FROM SLA WHERE uddislaId = :id)"
				+ " AND g.sla = s.sla";
		LOGGER.info(query);
		try {
			return (List) entityManager.createQuery(query)
					.setParameter("serviceId", serviceId)
					.setParameter("id", uddislaId).getResultList();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UDDISLA> getServiceByName(String serviceName) {

		String query = "from UDDISLA WHERE uddislaId = ANY (select s.uddisla from SLA s,"
				+ " ServiceTerms st, GuaranteeTerms gt WHERE s.slaId = st.sla AND gt.serviceName "
				+ "= st.serviceName AND st.serviceName = :name)";
		LOGGER.info(query);
		try {
			return (List<UDDISLA>) entityManager.createQuery(query)
					.setParameter("name", serviceName).getResultList();
		} catch (SecurityException | IllegalStateException | RollbackException e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}
