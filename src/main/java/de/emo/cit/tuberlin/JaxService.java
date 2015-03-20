package de.emo.cit.tuberlin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ModelConfiguration;
import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.config.GenerateUUID;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.ThesisService;

@Path("/webservice")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class JaxService {

	@Autowired
	Emo emo;
	@Autowired
	Track track;

	@SuppressWarnings("rawtypes")
	@Autowired
	ThesisService thesisServive;

	@Autowired
	private UDDI uddi;
	@Autowired
	private UDDISLA uddisla;

	@SuppressWarnings("resource")
	@GET
	public Track test() {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		emo.setNom("Mon nom est Emo Leumassi Ferdinand Frederic");
		track.setSinger("je suis le singer new singer");
		track.setTitle("voici le titel");
		track.setEmo(emo);
		return track;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@GET
	@Path("/all/{description}")
	// @POST
	// @PATH("/ALL/{DESCRIPTION}")
	public Response getAll(@PathParam("description") String description) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		uddi.setDescription(description);
		thesisServive.setClazz(UDDI.class);
		thesisServive.createEntity(uddi);
		return Response.status(200).entity(uddi).build();
	}

	@GET
	@Path("/uddisla")
	public Response uddiTest1() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		uddisla.setUddislaId(GenerateUUID.newUUID());
		uddisla.setDescription("uddi sla test with UUID");
		uddisla.setEmail("emo@cit.com");
		uddisla.setName("my name");
		uddisla.setPhone("015478");
		uddisla.setState("pending");
		uddisla.setVersion("1.0");
		thesisServive.setClazz(UDDISLA.class);
		thesisServive.createEntity(uddisla);
		return Response.status(200).entity(uddisla).build();	}
}
