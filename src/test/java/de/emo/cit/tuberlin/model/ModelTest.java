package de.emo.cit.tuberlin.model;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.help.ThesisHelp;

@RunWith(MockitoJUnitRunner.class)
@Transactional
public class ModelTest {

	@Mock
	EntityManager entityManager;

	@Mock
	Query query;

	@Mock
	UDDISLA uddisla;
	
	@Mock
	UDDI uddi;
	
	@Mock
	SLA sla;

	@Mock
	OverviewDoc overviewDoc;
	
	@Mock
	ServiceTerms serviceTerms;
	
	@Mock
	GuaranteeTerms guaranteeTerms;
	
	@Mock
	KeyPerformanceIndicator keyPerformanceIndicator;
	
	@Before
	public void setUp() {
		when(entityManager.find((Class<?>) any(), any())).thenReturn(null);
	}

	@Test
	public void testUDDISLA() {
		uddisla.setUddislaId(ThesisHelp.newUUID());
		uddisla.setDescription("a uddisla description test ...");
		uddisla.setEmail("myemail@email.com");
		uddisla.setName("Test");
		uddisla.setPhone("0027369");
		uddisla.setState("pending");
		uddisla.setVersion("1.4");
		entityManager.persist(uddisla);
		assertNotNull(uddisla);
	}
	
	@Test
	public void testUDDI() {
		uddi.setUddiId(ThesisHelp.newUUID());
		uddi.setDescription("a uddi description test ...");
		entityManager.persist(uddi);
		assertNotNull(uddi);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSLA() {
		sla.setSlaId(ThesisHelp.newUUID());
		sla.setDescription("a sla description test ...");
		sla.setEndTime(new Date(2017, 02, 15));
		sla.setStartTime(new Date(2015, 02, 15));
		entityManager.persist(sla);
		assertNotNull(sla);
	}
	
	@Test
	public void testOverviewDoc() {
		overviewDoc.setOverviewDocId(ThesisHelp.newUUID());
		overviewDoc.setDescription("a overviewdoc description test....");
		overviewDoc.setOverviewURL("http://mytest.com/test.wsdl");
		entityManager.persist(overviewDoc);
		assertNotNull(overviewDoc);
	}
	
	@Test
	public void testServiceTerms() {
		serviceTerms.setServiceTermId(ThesisHelp.newUUID());
		entityManager.persist(serviceTerms);
		assertNotNull(serviceTerms);
	}
	
	@Test
	public void testGuaranteeTerms() {
		guaranteeTerms.setGuaranteeTermId(ThesisHelp.newUUID());
		guaranteeTerms.setObligated("provider");
		guaranteeTerms.setServiceName("Strato");
		entityManager.persist(guaranteeTerms);
		assertNotNull(guaranteeTerms);
	}
	
	@Test
	public void testKeyPerformance() {
		keyPerformanceIndicator.setKeyPerformanceIndicatorId(ThesisHelp.newUUID());
		keyPerformanceIndicator.setDescription("a kpi description");
		keyPerformanceIndicator.setDesignation("availability");
		keyPerformanceIndicator.setQualifyingCondiction("10%");
		keyPerformanceIndicator.setTargetValue("90%");
		entityManager.persist(keyPerformanceIndicator);
		assertNotNull(keyPerformanceIndicator);
	}
}
