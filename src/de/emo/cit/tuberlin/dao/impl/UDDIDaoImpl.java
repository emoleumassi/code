package de.emo.cit.tuberlin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.dao.UDDIDao;
import de.emo.cit.tuberlin.model.UDDI;

//@Repository
public class UDDIDaoImpl implements UDDIDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UDDIDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	private Session session = sessionFactory.getCurrentSession();

	public UDDIDaoImpl() {
	}

	public UDDIDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<UDDI> listUDDI() {

		@SuppressWarnings("unchecked")
		List<UDDI> listUDDI = session.createQuery("from Person").list();
		for (UDDI uddi : listUDDI)
			LOGGER.info("UDDI List:: " + uddi);
		return listUDDI;
	}

	@Override
	@Transactional
	public UDDI getUDDIById(int id) {
		UDDI uddi = (UDDI) session.load(UDDI.class, new Integer(id));
		LOGGER.info("UDDI loaded successfully, Person details= " + uddi);
		return uddi;
	}

}
