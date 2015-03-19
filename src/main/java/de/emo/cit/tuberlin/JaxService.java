package de.emo.cit.tuberlin;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.model.UDDI;
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

	UDDI uddi = new UDDI();

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
	@Path("/all")
	// @POST
	// @PATH("/ALL/{DESCRIPTION}")
	public Response getAll() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		uddi.setDescription("This a just a test description with response");
		thesisServive.setClazz(UDDI.class);
		thesisServive.createEntity(uddi);
		return Response.status(200).entity(uddi).build();
	}

	@GET
	@Path("/alltt")
	public List<UDDI> uddiTest1() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		return thesisServive.listEntity();
	}
}