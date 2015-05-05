package de.emo.cit.tuberlin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.help.CheckJsonData;
import de.emo.cit.tuberlin.help.ResponseHelp;
import de.emo.cit.tuberlin.help.ThesisHelp;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDI;
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

	Response response;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThesisController.class);

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

		long startTime = System.currentTimeMillis();

		if (Objects.isNull(thesisRoot.getUddisla()))
			response = ResponseHelp.currentResponse(
					ResponseHelp.INTERNAL_SERVER_ERROR,
					"Internal Server Error, please post a document!");
		else {
			new CheckJsonData(thesisRoot, getService);
			postService.createServices(thesisRoot);
			response = ResponseHelp
					.currentResponse(ResponseHelp.OK, thesisRoot);
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to POST: " + elapsedTime + " ms");

		return response;
	}

	@GET
	public Response getAll() {

		long startTime = System.currentTimeMillis();
		setResponse(getService.getAllEntities());
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to get All: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/services/{serviceName}")
	public Response getServiceByName(
			@PathParam("serviceName") String serviceName) {

		long startTime = System.currentTimeMillis();
		setResponse(getService.getServiceByName(serviceName));
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time get serviceByName: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/services/{serviceName}/kpi")
	public Response getServiceByKPI(
			@PathParam("serviceName") String serviceName,
			@Context UriInfo uriInfo) {

		long startTime = System.currentTimeMillis();

		MultivaluedMap<String, String> hashMap = uriInfo.getQueryParameters();
		boolean notEmpty = hashMap.containsKey("availability")
				|| hashMap.containsKey("latency")
				|| hashMap.containsKey("mttr") || hashMap.containsKey("mtbf")
				|| hashMap.containsKey("responsetime");
		if (!notEmpty)
			setResponse(new ArrayList<>());
		else 
			setResponse(buildQuery(serviceName, hashMap));

		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to get service with kpi: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/{uddislaIdName}")
	public Response getUDDISLAById(
			@PathParam("uddislaIdName") String uddislaIdName) {

		long startTime = System.currentTimeMillis();
		setResponse(getService.getUDDISLAByIdName(uddislaIdName));
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to get UDDISLA by Id: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/{uddislaId}/uddi")
	public Response getUDDI(@PathParam("uddislaId") String uddislaId) {

		long startTime = System.currentTimeMillis();
		ThesisHelp.validateUUID(uddislaId, "uddislaID");

		getService.setClazz(UDDI.class);
		setResponse(getService.getUddiOrSla(uddislaId));
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to get UDDI: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/{uddislaId}/sla")
	public Response getSLA(@PathParam("uddislaId") String uddislaId) {

		long startTime = System.currentTimeMillis();
		ThesisHelp.validateUUID(uddislaId, "uddislaID");

		getService.setClazz(SLA.class);
		setResponse(getService.getUddiOrSla(uddislaId));
		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to get SLA: " + elapsedTime + " ms");

		return response;
	}

	@GET
	@Path("/{uddislaId}/sla/services/{serviceTermId}")
	public Response getTermById(@PathParam("uddislaId") String uddislaId,
			@PathParam("serviceTermId") String serviceTermId) {

		long startTime = System.currentTimeMillis();

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		ThesisHelp.validateUUID(serviceTermId, "serviceID");

		setResponse(getService.getTerms(uddislaId, serviceTermId));

		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time get termById: " + elapsedTime + " ms");

		return response;
	}

	@DELETE
	@Path("/{uddislaId}")
	public Response delete(@PathParam("uddislaId") String uddislaId) {

		long startTime = System.currentTimeMillis();

		ThesisHelp.validateUUID(uddislaId, "uddislaID");
		deleteService.deleteById(uddislaId);
		String message = "Webservice with the id: " + uddislaId
				+ " successfully deleted";
		response = ResponseHelp.currentResponse(ResponseHelp.NO_CONTENT,
				message);

		long elapsedTime = System.currentTimeMillis() - startTime;
		LOGGER.info("Time to delete an uddisla: " + elapsedTime + " ms");

		return response;
	}

	private List buildQuery(String serviceName,
			MultivaluedMap<String, String> hashMap) {
		String query = "";
		int count = 0;

		for (Entry<String, List<String>> entry : hashMap.entrySet()) {
			if (count > 0)
				query += " AND";
			String element = entry.getKey().toLowerCase().trim();
			short value = Short.valueOf(entry.getValue().get(0));

			if (element.equals("latency"))
				query += " latency <= " + value;
			else if (element.equals("mttr"))
				query += " mttr <= " + value;
			else if (element.equals("responsetime"))
				query += " responseTime <= " + value;
			else if (element.equals("availability"))
				query += " availability >= " + value;
			else if (element.equals("mtbf"))
				query += " mtbf >= " + value;
			count++;
		}
		LOGGER.info("KPI-Query:" + query);
		return getService.getServiceByKPI(serviceName, query);
	}

	private Response setResponse(Object element) {
		if ((element instanceof List && ((List) element).isEmpty())
				|| Objects.isNull(element))
			response = ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			response = ResponseHelp.currentResponse(ResponseHelp.OK, element);
		return response;
	}
}
