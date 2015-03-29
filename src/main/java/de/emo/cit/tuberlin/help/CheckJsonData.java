package de.emo.cit.tuberlin.help;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import de.emo.cit.tuberlin.exception.ClientRequestException;
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

		if (serviceTermsList.size() != guaranteeTermsList.size()) {

			RequestHelp.BAD_REQUEST_MESSAGE += "You have more serviceTerms as guaranteeTerms "
					+ "or more guaranteeTerms as serviceTerms.\n";
			throw new ClientRequestException(RequestHelp.BAD_REQUEST_STATUS,
					RequestHelp.BAD_REQUEST_MESSAGE);
		}

		for (ServiceTerms serviceTerms : serviceTermsList)
			checkServiceParam(serviceTerms.getName(),
					serviceTerms.getDesignation(),
					serviceTerms.getCostPerUnitOfAccount(),
					String.valueOf(serviceTerms.getUnitOfAccount()));

		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {

			if (guaranteeTerms.getObligated().isEmpty())
				guaranteeTerms.setObligated("provider");

			String serviceName = guaranteeTerms.getServiceName();
			checkGuaranteeParam(serviceName);

			boolean nameEquals = false;
			for (ServiceTerms serviceTerms : serviceTermsList) {
				if (serviceTerms.getName().equals(serviceName)) {
					nameEquals = true;
					break;
				}
			}

			if (!nameEquals) {
				RequestHelp.BAD_REQUEST_MESSAGE += "'" + serviceName
						+ "' doesn't have any reference in the serviceTerms.\n";
				throw new ClientRequestException(RequestHelp.BAD_REQUEST_STATUS,
						RequestHelp.BAD_REQUEST_MESSAGE);
			}

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

	private void checkServiceParam(@QueryParam("name") String name,
			@QueryParam("designation") String designation,
			@QueryParam("costPerUnitOfAccount") String costPerUnitOfAccount,
			@QueryParam("unitOfAccount") String unitOfAccount) {

		throwException(name, "name");
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
			RequestHelp.BAD_REQUEST_MESSAGE += "'" + element
					+ "' is mandatory.\n";
			throw new ClientRequestException(RequestHelp.BAD_REQUEST_STATUS,
					RequestHelp.BAD_REQUEST_MESSAGE);
		}
	}
}
