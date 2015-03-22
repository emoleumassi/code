package de.emo.cit.tuberlin;

import java.util.List;

import javax.ws.rs.Consumes;
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
import de.emo.cit.tuberlin.model.ServiceTerms;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.ThesisService;

@Path("/webservice")
@Produces(MediaType.APPLICATION_JSON)
public class POSTService {

	// curl -H "Content-Type: application/json" -d
	// '@/media/ferdinand/Emo/test.json' localhost:8080/thesis/webservice/send
	@SuppressWarnings("rawtypes")
	@Autowired
	ThesisService thesisServive;

	private SLA sla;
	private UDDI uddi;
	private UDDISLA uddisla;
	private OverviewDoc overviewDoc;
	// private ServiceTerms serviceTerms;
	private List<ServiceTerms> serviceTermsList;

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

	private void setUUID(ThesisRoot thesisRoot) {

		uddisla = thesisRoot.getUddisla();
		uddisla.setUddislaId(GenerateUUID.newUUID());

		uddi = uddisla.getUddi();
		uddi.setUddiId(GenerateUUID.newUUID());

		sla = uddisla.getSla();
		sla.setSlaId(GenerateUUID.newUUID());

		overviewDoc = uddi.getOverviewDoc();
		overviewDoc.setOverviewDocId(GenerateUUID.newUUID());

		serviceTermsList = sla.getServiceTerms();
		for (ServiceTerms serviceTerms : serviceTermsList) {
			serviceTerms.setServiceTermId(GenerateUUID.newUUID());
		}

		setEntity(sla);
		setEntity(overviewDoc);
		setEntity(uddi);
		setEntity(uddisla);

		// thesisServive.setClazz(SLA.class);
		// thesisServive.updateColumnById("uddislaId", "slaId",
		// uddisla.getUddislaId(), sla.getSlaId());

		// thesisServive.setClazz(UDDISLA.class);
		// UDDISLA uddisla = (UDDISLA)
		// thesisServive.getEntityById(this.uddisla.getUddislaId());
		// uddisla.setSla(sla);
	}

	@SuppressWarnings("unchecked")
	private void setEntity(Object object) {
		thesisServive.setClazz(Object.class);
		thesisServive.createEntity(object);
	}
}
