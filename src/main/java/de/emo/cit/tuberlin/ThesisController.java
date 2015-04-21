package de.emo.cit.tuberlin;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.help.CheckJsonData;
import de.emo.cit.tuberlin.help.ResponseHelp;
import de.emo.cit.tuberlin.help.ThesisHelp;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.DeleteService;
import de.emo.cit.tuberlin.service.GetService;
import de.emo.cit.tuberlin.service.PostService;

/**
 * 
 * @author emoleumassi
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
@Path("/webservices")
@Produces(MediaType.APPLICATION_JSON)
public class ThesisController {

	@Autowired
	GetService getService;
	@Autowired
	PostService postService;
	@Autowired
	DeleteService deleteService;

	public ThesisController() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(ThesisRoot thesisRoot) {

		if (Objects.isNull(thesisRoot.getUddisla()))
			return ResponseHelp.currentResponse(
					ResponseHelp.INTERNAL_SERVER_ERROR,
					"Internal Server Error, please post a document!");
		else {
			new CheckJsonData(thesisRoot);
			postService.createServices(thesisRoot);
			return ResponseHelp.currentResponse(ResponseHelp.OK, thesisRoot);
		}
	}

	@GET
	public Response getAll() {

		List entities = getService.getAllEntities();
		if (entities.isEmpty())
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, entities);
	}

	@GET
	@Path("/services/{serviceName}")
	public Response getServiceByName(
			@PathParam("serviceName") String serviceName) {

		List<UDDISLA> uddislas = getService.getServiceByName(serviceName);
		if (uddislas.isEmpty())
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, uddislas);
	}

	@GET
	@Path("/services/{serviceName}/kpi")
	public Response getServiceByKPI(
			@PathParam("serviceName") String serviceName,
			@QueryParam("name") String name, @QueryParam("value") String value) {

		List<UDDISLA> uddislas = getService.getServiceByName(serviceName);
		if (uddislas.isEmpty())
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else {
//			for (UDDISLA uddisla : uddislas) {
//				for (GuaranteeTerms terms : uddisla.getSla()
//						.getGuaranteeTerms()) {
//					for (KeyPerformanceIndicator kpi : terms
//							.getKeyPerformanceIndicator()) {
//						if (name.equals(kpi.getName()) && value.) {
//							
//						} 
//					}
//				}
//			}
			return ResponseHelp.currentResponse(ResponseHelp.OK, uddislas);
		}
	}

	@GET
	@Path("/{uddislaIdName}")
	public Response getUDDISLAById(
			@PathParam("uddislaIdName") String uddislaIdName) {

		List<UDDISLA> uddisla = getService.getUDDISLAByIdName(uddislaIdName);
		if (uddisla.isEmpty())
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, uddisla);
	}

	@GET
	@Path("/{uddislaId}/uddi")
	public Response getUDDI(@PathParam("uddislaId") String uddislaId) {

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		getService.setClazz(UDDI.class);
		UDDI uddi = (UDDI) getService.getUddiOrSla(uddislaId);
		if (Objects.isNull(uddi))
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, uddi);
	}

	@GET
	@Path("/{uddislaId}/sla")
	public Response getSLA(@PathParam("uddislaId") String uddislaId) {

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		getService.setClazz(SLA.class);
		SLA sla = (SLA) getService.getUddiOrSla(uddislaId);
		if (Objects.isNull(sla))
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, sla);
	}

	@GET
	@Path("/{uddislaId}/sla/services/{serviceTermId}")
	public Response getTermById(@PathParam("uddislaId") String uddislaId,
			@PathParam("serviceTermId") String serviceTermId) {

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		ThesisHelp.validateUUID(serviceTermId, "serviceID");
		List terms = getService.getTerms(uddislaId, serviceTermId);
		if (terms.isEmpty())
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, terms);
	}

	@DELETE
	@Path("/{uddislaId}")
	public Response delete(@PathParam("uddislaId") String uddislaId) {

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		deleteService.deleteById(uddislaId);
		String message = "Webservice with the id: " + uddislaId
				+ " successfully deleted";
		return ResponseHelp.currentResponse(ResponseHelp.NO_CONTENT, message);
	}
}
