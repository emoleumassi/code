package de.emo.cit.tuberlin;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * 
 * @author emoleumassi
 * 
 */
public class ThesisControllerTest {

	private static final int PORT = 8080;
	private static final String LOCALHOST = "http://localhost";
	private static final String HOST = "http://emo.cit.tu-berlin.de";
	private static final String GLOBAL_PATH = "/thesis/webservices/";
	
	private static final URI URI = getBaseURI();

	private HttpServer httpServer;
	private Client client;

	private static URI getBaseURI() {
		return UriBuilder.fromUri(LOCALHOST).port(PORT).build();
	}

	@Before
	public void setUp() throws IllegalArgumentException, NullPointerException,
			IOException {
		httpServer = com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory
				.createHttpServer(URI);
		httpServer.start();
	}

	@Test
	public void testGetUDDISLA() {

		client = Client.create(new DefaultClientConfig());
		WebResource webResource = client.resource(getBaseURI());
		ClientResponse response = webResource.path(GLOBAL_PATH)
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		assertEquals(200, response.getStatus());
	}

	@After()
	public void stopServer() {
		httpServer.stop();
	}
}
