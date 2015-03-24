package de.emo.cit.tuberlin.help;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import de.emo.cit.tuberlin.exception.ElementNotFoundException;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ServiceTerms;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author emoleumassi
 * 
 */
public class CheckJsonData {

	public CheckJsonData(ThesisRoot thesisRoot) {

		UDDISLA uddisla = thesisRoot.getUddisla();
		OverviewDoc overviewDoc = uddisla.getUddi().getOverviewDoc();
		SLA sla = uddisla.getSla();
		List<ServiceTerms> serviceTermsList = sla.getServiceTerms();
		List<GuaranteeTerms> guaranteeTermsList = sla.getGuaranteeTerms();

		for (ServiceTerms serviceTerms : serviceTermsList)
			checkServiceParam(serviceTerms.getDesignation(),
					serviceTerms.getCostPerUnitOfAccount(),
					String.valueOf(serviceTerms.getUnitOfAccount()));

		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {

			checkGuaranteeParam(guaranteeTerms.getServiceName());

			List<KeyPerformanceIndicator> kpiList = guaranteeTerms
					.getKeyPerformanceIndicator();
			for (KeyPerformanceIndicator kpi : kpiList) {
				checkKPIParam(kpi.getDesignation(),
						kpi.getQualifyingCondiction(), kpi.getTargetValue());
			}
		}

		checkQueryParam(uddisla.getName(), uddisla.getVersion(),
				overviewDoc.getOverviewURL(), sla.getStartTime(),
				sla.getEndTime());
	}

	private void checkQueryParam(@QueryParam("name") String name,
			@QueryParam("version") String version,
			@QueryParam("overviewURL") String overviewURL,
			@QueryParam("startTime") Date startTime,
			@QueryParam("endTime") Date endTime) {

		throwException(name, "name");
		throwException(version, "version");
		throwException(overviewURL, "overviewURL");
		throwException(startTime.toString(), "startTime");
		throwException(endTime.toString(), "endTime");
	}

	private void checkServiceParam(
			@QueryParam("designation") String designation,
			@QueryParam("costPerUnitOfAccount") String costPerUnitOfAccount,
			@QueryParam("unitOfAccount") String unitOfAccount) {

		throwException(designation, "designation");
		throwException(costPerUnitOfAccount, "costPerUnitOfAccount");
		throwException(unitOfAccount, "unitOfAccount");
	}

	private void checkGuaranteeParam(
			@QueryParam("serviceName") String serviceName) {

		throwException(serviceName, "serviceName");

	}

	private void checkKPIParam(@QueryParam("designation") String designation,
			@QueryParam("qualifyingCondiction") String qualifyingCondiction,
			@QueryParam("targetValue") String targetValue) {

		throwException(designation, "designation");
		throwException(qualifyingCondiction, "qualifyingCondiction");
		throwException(targetValue, "targetValue");

	}

	private void throwException(String value, String element) {

		if (value.isEmpty() || value == null) {
			String message = "HTTP/1.1 "
					+ Response.Status.BAD_REQUEST.getStatusCode()
					+ " Bad Request. '" + element + "' is mandatory.\n";
			throw new ElementNotFoundException(message);
		}
	}

	// public CheckJsonData(UriInfo uriInfo) {
	// checkQueryParam(uriInfo, "name");
	// checkQueryParam(uriInfo, "overviewURL");
	// checkQueryParam(uriInfo, "startTime");
	// checkQueryParam(uriInfo, "endTime");
	// checkQueryParam(uriInfo, "costPerUnitOfAccount");
	// checkQueryParam(uriInfo, "unitOfAccount");
	// checkQueryParam(uriInfo, "serviceName");
	// checkQueryParam(uriInfo, "designation");
	// checkQueryParam(uriInfo, "qualifyingCondiction");
	// checkQueryParam(uriInfo, "targetValue");
	// }
	//
	// private void checkQueryParam(UriInfo uriInfo, String element) {
	//
	// String value = uriInfo.getQueryParameters().getFirst(element);
	// if (value == null || value.isEmpty()) {
	// String message = "HTTP/1.1 "
	// + Response.Status.BAD_REQUEST.getStatusCode()
	// + " Bad Request. '" + element + "' is mandatory.\n";
	// throw new ElementNotFoundException(message);
	// }
	// }
}
