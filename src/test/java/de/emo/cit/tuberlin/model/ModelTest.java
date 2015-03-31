package de.emo.cit.tuberlin.model;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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

	@Before
	public void setUp() {
		when(entityManager.find((Class<?>) any(), any())).thenReturn(null);
	}

	@Test
	public void testUDDISLA() {
		uddisla.setUddislaId(ThesisHelp.newUUID());
		entityManager.persist(uddisla);
		assertNotNull(uddisla);
	}
}
