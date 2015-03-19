package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.service.UDDIService;

@Service("uddiService")
public class UDDIServiceImpl implements UDDIService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UDDIServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;	
//	@PersistenceContext
//	private EntityTransaction entityTransaction;

	@Override
	//@Transactional
	public List<UDDI> listUDDI() {

		@SuppressWarnings("unchecked")
		List<UDDI> listUDDI = entityManager.createQuery("from UDDI")
				.getResultList();
		for (UDDI uddi : listUDDI)
			LOGGER.info("UDDI List:: " + uddi);
		return listUDDI;
	}
	
	@Override
	//@Transactional
	public void createEntity(UDDI uddi) {
		
		try {
//			entityTransaction.begin();
	        entityManager.persist(uddi);
//	        entityTransaction.commit();
		} catch (Exception e) {
//			entityTransaction.rollback();
			LOGGER.info(e.getMessage());
		}
    }

	@Override
	//@Transactional
	public UDDI getUDDIById(int id) {
		// UDDI uddi = (UDDI) entityManager.load(UDDI.class, new Integer(id));
		// LOGGER.info("UDDI loaded successfully, Person details= " + uddi);
		// return uddi;
		return null;
	}

}
