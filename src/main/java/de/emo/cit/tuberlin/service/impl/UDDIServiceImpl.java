package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.service.UDDIService;

//@Repository
public class UDDIServiceImpl implements UDDIService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UDDIServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	private Session session = sessionFactory.getCurrentSession();

	public UDDIServiceImpl() {
	}

	public UDDIServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//@Override
	@Transactional
	public List<UDDI> listUDDI() {

		@SuppressWarnings("unchecked")
		List<UDDI> listUDDI = session.createQuery("from Person").list();
		for (UDDI uddi : listUDDI)
			LOGGER.info("UDDI List:: " + uddi);
		return listUDDI;
	}

	//@Override
	@Transactional
	public UDDI getUDDIById(int id) {
		UDDI uddi = (UDDI) session.load(UDDI.class, new Integer(id));
		LOGGER.info("UDDI loaded successfully, Person details= " + uddi);
		return uddi;
	}

}
