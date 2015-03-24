package de.emo.cit.tuberlin;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.help.CheckJsonData;
import de.emo.cit.tuberlin.help.ThesisHelp;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
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

	private static final Logger LOGGER = LoggerFactory
			.getLogger(POSTService.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	ThesisService thesisServive;

	private SLA sla;
	private UDDI uddi;
	private UDDISLA uddisla;
	private OverviewDoc overviewDoc;
	private List<ServiceTerms> serviceTermsList;
	private List<GuaranteeTerms> guaranteeTermsList;
	private List<KeyPerformanceIndicator> keyPerformanceIndicatorList;

	@SuppressWarnings({ "resource" })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/send")
	public Response postUDDISLA(ThesisRoot thesisRoot) {

		new CheckJsonData(thesisRoot);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		setUUID(thesisRoot);

		return Response.status(Response.Status.OK).entity(thesisRoot)
				.entity("Add json to the server!!!").build();
	}

	private void setUUID(ThesisRoot thesisRoot) {

		uddisla = thesisRoot.getUddisla();

		uddisla.setUddislaId(ThesisHelp.newUUID());

		uddi = uddisla.getUddi();
		uddi.setUddiId(ThesisHelp.newUUID());

		sla = uddisla.getSla();
		sla.setSlaId(ThesisHelp.newUUID());

		overviewDoc = uddi.getOverviewDoc();
		overviewDoc.setOverviewDocId(ThesisHelp.newUUID());

		serviceTermsList = sla.getServiceTerms();
		guaranteeTermsList = sla.getGuaranteeTerms();
		for (ServiceTerms serviceTerms : serviceTermsList) {
			serviceTerms.setServiceTermId(ThesisHelp.newUUID());
			setEntity(serviceTerms);
		}
		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {
			guaranteeTerms.setGuaranteeTermId(ThesisHelp.newUUID());
			keyPerformanceIndicatorList = guaranteeTerms
					.getKeyPerformanceIndicator();
			for (KeyPerformanceIndicator keyPerformanceIndicator : keyPerformanceIndicatorList) {
				keyPerformanceIndicator.setKeyPerformanceIndicatorId(ThesisHelp
						.newUUID());
				setEntity(keyPerformanceIndicator);
			}
			setEntity(guaranteeTerms);
		}

		setEntity(sla);
		setEntity(overviewDoc);
		setEntity(uddi);
		setEntity(uddisla);

		// thesisServive.setClazz(SLA.class);
		// thesisServive.updateColumnById("uddislaId", "slaId",
		// uddisla.getUddislaId(), sla.getSlaId());
	}

	@SuppressWarnings("unchecked")
	private void setEntity(Object object) {
		thesisServive.setClazz(Object.class);
		thesisServive.createEntity(object);
	}
}
