package de.emo.cit.tuberlin;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ModelConfiguration;
import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.GetService;

@SuppressWarnings("unchecked")
@Path("/uddisla")
@Produces(MediaType.APPLICATION_JSON)
public class GetController {

	// @Autowired
	// private SLA sla;
	// @Autowired
	// private UDDI uddi;
	// @Autowired
	// private UDDISLA uddisla;
	// @Autowired
	// private OverviewDoc overviewDoc;

	@SuppressWarnings("rawtypes")
	@Autowired
	GetService getService;

	@SuppressWarnings("resource")
	public GetController() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class, ModelConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);
	}

	@SuppressWarnings({ "rawtypes" })
	@GET
	@Path("/")
	public Response getAll() {

		List entities = getService.getAllEntities();
		return Response.status(200).entity(entities).build();
	}

	@GET
	@Path("/{uddislaIdName}")
	public Response getUDDISLAById(
			@PathParam("uddislaIdName") String uddislaIdName) {

		List<UDDISLA> uddisla = getService.getUDDISLAByIdName(uddislaIdName);
		return Response.status(200).entity(uddisla).build();
	}

	@GET
	@Path("/{uddislaId}/uddi")
	public Response getUDDI(@PathParam("uddislaId") String uddislaId) {

		getService.setClazz(UDDI.class);
		UDDI uddi = (UDDI) getService.getUddiOrSla(uddislaId);
		return Response.status(200).entity(uddi).build();
	}

	@GET
	@Path("/{uddislaId}/sla")
	public Response getSLA(@PathParam("uddislaId") String uddislaId) {

		getService.setClazz(SLA.class);
		SLA sla = (SLA) getService.getUddiOrSla(uddislaId);
		return Response.status(200).entity(sla).build();
	}

	@GET
	@Path("/{uddislaId}/sla/{termId}")
	public Response getTerms(@PathParam("uddislaId") String uddislaId,
			@PathParam("termId") String termId) {

		getService.setClazz(GuaranteeTerms.class);
		GuaranteeTerms guaranteeTerms = (GuaranteeTerms) getService.getTerms(
				uddislaId, termId, "guaranteeTermId");
		return Response.status(200).entity(guaranteeTerms).build();
	}
}
