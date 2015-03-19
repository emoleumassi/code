package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.service.UDDIService;

@Transactional
@Service("uddiService")
public class UDDIServiceImpl implements UDDIService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UDDIServiceImpl.class);

//	@Autowired
//	private EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	private EntityManager entityManager;
//	= entityManagerFactory
//			.createEntityManager();

//	public UDDIServiceImpl() {
//	}
//
//	public UDDIServiceImpl(EntityManagerFactory entityManagerFactory) {
//		this.entityManagerFactory = entityManagerFactory;
//	}

	@Override
	public List<UDDI> listUDDI() {

		@SuppressWarnings("unchecked")
		List<UDDI> listUDDI = entityManager.createQuery("from UDDI")
				.getResultList();
		for (UDDI uddi : listUDDI)
			LOGGER.info("UDDI List:: " + uddi);
		return listUDDI;
	}

	@Override
	public UDDI getUDDIById(int id) {
		// UDDI uddi = (UDDI) entityManager.load(UDDI.class, new Integer(id));
		// LOGGER.info("UDDI loaded successfully, Person details= " + uddi);
		// return uddi;
		return null;
	}

}
