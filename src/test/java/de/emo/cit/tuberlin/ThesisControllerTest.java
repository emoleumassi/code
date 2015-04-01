package de.emo.cit.tuberlin;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.emo.cit.tuberlin.bootstrap.PropertyConfiguration;
import de.emo.cit.tuberlin.help.ThesisHelp;
import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 * 
 */
@RunWith(MockitoJUnitRunner.class)
@Transactional
public class ThesisControllerTest {

	@Mock
	EntityManager entityManager;

	@Mock
	Query query;

	@Mock
	UDDISLA uddisla;

	@Mock
	static PropertyConfiguration propertyConfiguration;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThesisControllerTest.class);

	private static final int PORT = 8080;
	private static final String LOCALHOST = "http://localhost";
	private static final String HOST = "http://emo.cit.tu-berlin.de";
	private static final String GLOBAL_PATH = "/webservices/";

	private static final URI URI = getBaseURI();

	private HttpServer httpServer;
	private Client client;

	private static URI getBaseURI() {
		return UriBuilder.fromUri(LOCALHOST).port(PORT).build();
	}

	private ClientResponse getBaseResource(String path) {

		client = Client.create(new DefaultClientConfig());
		WebResource webResource = client.resource(getBaseURI());
		return webResource.path(path).accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
	}

	@Before
	public void setUp() throws IllegalArgumentException, NullPointerException,
			IOException {
		httpServer = com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory
				.createHttpServer(URI);
		httpServer.start();
	}

	@Test
	public void testGetAll() {
		LOGGER.info(getBaseResource(GLOBAL_PATH).toString());
		assertEquals(200, getBaseResource(GLOBAL_PATH).getStatus());
	}

	@Test
	public void testGetUDDIById() {

		String id = ThesisHelp.newUUID();
		uddisla.setUddislaId(id);
		entityManager.persist(uddisla);

		when(entityManager.createQuery("FROM UDDISLA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(uddisla);
		when(uddisla.getUddislaId()).thenReturn(id);

		String path = GLOBAL_PATH + uddisla.getUddislaId();
		LOGGER.info(getBaseResource(path).toString());
		assertEquals(200, getBaseResource(path).getStatus());
	}
	
	@Test
	public void testGetUDDIByName() {

		String name = "emoleumassi";
		uddisla.setUddislaId(ThesisHelp.newUUID());
		uddisla.setName(name);
		entityManager.persist(uddisla);

		when(entityManager.createQuery("FROM UDDISLA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(uddisla);
		when(uddisla.getName()).thenReturn(name);

		String path = GLOBAL_PATH + uddisla.getName();
		LOGGER.info(getBaseResource(path).toString());
		assertEquals(200, getBaseResource(path).getStatus());
	}

	@After()
	public void stopServer() {
		httpServer.stop();
	}
}
