package de.emo.cit.tuberlin;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ModelConfiguration;
import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.config.GenerateUUID;
import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.ThesisService;

@Path("/webservice")
@Produces(MediaType.APPLICATION_JSON)
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
	@Autowired
	private OverviewDoc overviewDoc;
	@Autowired
	private SLA sla;

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

	@SuppressWarnings({ "resource" })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/send")
	public Response postUDDISLA(ThesisRoot thesisRoot) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		setUUID(thesisRoot);
		return Response.status(200).entity(thesisRoot).build();
	}

	@SuppressWarnings({ "resource", "deprecation" })
	@GET
	@Path("/uddisla")
	// @Path("/all/{description}")
	// public Response getAll(@PathParam("description") String description)
	public Response getUDDISLA() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		sla.setSlaId(GenerateUUID.newUUID());
		sla.setDescription("SLA description");
		sla.setEndTime(new Date(2017, 03, 22));
		sla.setStartTime(new Date(2015, 03, 22));
		setEntity(sla);

		overviewDoc.setOverviewDocId(GenerateUUID.newUUID());
		overviewDoc.setDescription("overviewDoc description");
		overviewDoc.setOverviewuRL("http://emo.xxx.emo.wsdl");
		setEntity(overviewDoc);

		uddi.setUddiId(GenerateUUID.newUUID());
		uddi.setDescription("uddi description test");
		uddi.setOverviewDoc(overviewDoc);
		setEntity(uddi);

		uddisla.setUddislaId(GenerateUUID.newUUID());
		uddisla.setDescription("uddi sla test with UUID");
		uddisla.setEmail("emo@cit.com");
		uddisla.setName("my name");
		uddisla.setPhone("015478");
		uddisla.setState("pending");
		uddisla.setVersion("1.0");
		uddisla.setUddi(uddi);
		// sla.setUddisla(uddisla);
		uddisla.setSla(sla);
		setEntity(uddisla);
		return Response.status(200).entity(uddisla).build();
	}

	@SuppressWarnings("unchecked")
	private void setEntity(Object object) {
		thesisServive.setClazz(Object.class);
		thesisServive.createEntity(object);
	}
	
	private void setUUID(ThesisRoot thesisRoot){
		
		UDDISLA uddisla = thesisRoot.getUddisla();
		uddisla.setUddislaId(GenerateUUID.newUUID());
		
		UDDI uddi = uddisla.getUddi();
		uddi.setUddiId(GenerateUUID.newUUID());
		
		SLA sla = uddisla.getSla();
		sla.setSlaId(GenerateUUID.newUUID());	
		
		OverviewDoc overviewDoc = uddi.getOverviewDoc();
		overviewDoc.setOverviewDocId(GenerateUUID.newUUID());
	}
}
