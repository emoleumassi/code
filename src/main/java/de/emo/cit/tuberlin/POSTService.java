package de.emo.cit.tuberlin;

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
import de.emo.cit.tuberlin.help.CheckJsonData;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.service.PostService;

/**
 * 
 * @author emoleumassi
 * 
 */
@Path("/webservice")
@Produces(MediaType.APPLICATION_JSON)
public class POSTService {

//	private static final Logger LOGGER = LoggerFactory
//			.getLogger(POSTService.class);
//
//	@SuppressWarnings("rawtypes")
//	@Autowired
//	ThesisService thesisServive;
//
//	private SLA sla;
//	private UDDI uddi;
//	private UDDISLA uddisla;
//	private OverviewDoc overviewDoc;
//	private List<ServiceTerms> serviceTermsList;
//	private List<GuaranteeTerms> guaranteeTermsList;
//	private List<KeyPerformanceIndicator> kpiList;
	
	@Autowired
	private PostService postService;

	@SuppressWarnings({ "resource" })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/send")
	public Response postUDDISLA(ThesisRoot thesisRoot) {

		new CheckJsonData(thesisRoot);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		postService.setUUID(thesisRoot);

		return Response.status(Response.Status.OK).entity(thesisRoot).build();
	}

//	private void setUUID(ThesisRoot thesisRoot) {
//
//		uddisla = thesisRoot.getUddisla();
//		uddisla.setUddislaId(ThesisHelp.newUUID());
//
//		uddi = uddisla.getUddi();
//		uddi.setUddiId(ThesisHelp.newUUID());
//
//		sla = uddisla.getSla();
//		sla.setSlaId(ThesisHelp.newUUID());
//
//		overviewDoc = uddi.getOverviewDoc();
//		overviewDoc.setOverviewDocId(ThesisHelp.newUUID());
//
//		serviceTermsList = sla.getServiceTerms();
//		guaranteeTermsList = sla.getGuaranteeTerms();
//		for (ServiceTerms serviceTerms : serviceTermsList)
//			serviceTerms.setServiceTermId(ThesisHelp.newUUID());
//
//		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {
//			guaranteeTerms.setGuaranteeTermId(ThesisHelp.newUUID());
//			kpiList = guaranteeTerms.getKeyPerformanceIndicator();
//			for (KeyPerformanceIndicator kpi : kpiList)
//				kpi.setKeyPerformanceIndicatorId(ThesisHelp.newUUID());
//		}
//
//		setEntity(uddisla);
//		LOGGER.info("succefull insert all the data!!!");
//
//		updateEntities();
//	}
//
//	@SuppressWarnings("unchecked")
//	private void setEntity(Object object) {
//		thesisServive.setClazz(Object.class);
//		thesisServive.createEntity(object);
//	}
//
//	private void updateEntities() {
//		thesisServive.updateColumnById("SLA", "uddislaId", "slaId",
//				uddisla.getUddislaId(), sla.getSlaId());
//
//		thesisServive.updateColumnById("UDDI", "uddislaId", "uddiId",
//				uddisla.getUddislaId(), uddi.getUddiId());
//
//		thesisServive.updateColumnById("OverviewDoc", "uddiId",
//				"overviewDocId", uddi.getUddiId(),
//				overviewDoc.getOverviewDocId());
//
//		for (ServiceTerms serviceTerms : serviceTermsList)
//			thesisServive.updateColumnById("ServiceTerms", "slaId",
//					"serviceTermId", sla.getSlaId(),
//					serviceTerms.getServiceTermId());
//
//		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {
//			thesisServive.updateColumnById("GuaranteeTerms", "slaId",
//					"guaranteeTermId", sla.getSlaId(),
//					guaranteeTerms.getGuaranteeTermId());
//			kpiList = guaranteeTerms.getKeyPerformanceIndicator();
//			for (KeyPerformanceIndicator kpi : kpiList)
//				thesisServive.updateColumnById("KeyPerformanceIndicator",
//						"guaranteeTermId", "keyPerformanceIndicatorId",
//						guaranteeTerms.getGuaranteeTermId(),
//						kpi.getKeyPerformanceIndicatorId());
//		}
//	}
}
