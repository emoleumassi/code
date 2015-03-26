package de.emo.cit.tuberlin;

import java.util.List;

import javax.ws.rs.GET;
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
import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.GetService;

@Path("/uddisla")
@Produces(MediaType.APPLICATION_JSON)
public class GetController {

	@Autowired
	private SLA sla;
	@Autowired
	private UDDI uddi;
	@Autowired
	private UDDISLA uddisla;
	@Autowired
	private OverviewDoc overviewDoc;
	
	@Autowired
	GetService getService;
	
	@SuppressWarnings({ "resource", "rawtypes" })
	@GET
	@Path("/all")
	public Response getUDDISLA() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
		
		List entities = getService.getAllEntities();
		return Response.status(200).entity(entities).build();
	}

}
