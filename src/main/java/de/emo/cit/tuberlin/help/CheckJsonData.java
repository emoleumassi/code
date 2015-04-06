package de.emo.cit.tuberlin.help;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import de.emo.cit.tuberlin.exception.ClientRequestException;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.Reward;
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

		checkQueryParam(uddisla.getName(), uddisla.getVersion(),
				overviewDoc.getOverviewURL(), uddisla.getStartTime(),
				uddisla.getEndTime());

		if (serviceTermsList.size() != guaranteeTermsList.size()) {

			ResponseHelp.BAD_REQUEST_MESSAGE += "You have more serviceTerms as guaranteeTerms "
					+ "or more guaranteeTerms as serviceTerms.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}

		for (ServiceTerms serviceTerms : serviceTermsList)
			checkServiceParam(serviceTerms.getName(),
					serviceTerms.getServiceName());

		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {

			String serviceName = guaranteeTerms.getServiceName();
			Reward reward = guaranteeTerms.getReward();
			checkGuaranteeParam(serviceName, reward.getTimeInterval(),
					reward.getCount(), reward.getValueUnit());

			boolean nameEquals = false;
			for (ServiceTerms serviceTerms : serviceTermsList)
				if (serviceTerms.getServiceName().equals(serviceName)) {
					nameEquals = true;
					break;
				}

			if (!nameEquals) {
				ResponseHelp.BAD_REQUEST_MESSAGE += "'" + serviceName
						+ "' doesn't have any reference in the serviceTerms.\n";
				throw new ClientRequestException(
						ResponseHelp.BAD_REQUEST_STATUS,
						ResponseHelp.BAD_REQUEST_MESSAGE);
			}

			List<KeyPerformanceIndicator> kpiList = guaranteeTerms
					.getKeyPerformanceIndicator();
			for (KeyPerformanceIndicator kpi : kpiList) {
				checkKPIParam(kpi.getName(), kpi.getQualifyingCondiction(),
						kpi.getTargetValue());
			}
		}
	}

	private void checkQueryParam(@QueryParam("name") String name,
			@QueryParam("version") String version,
			@QueryParam("overviewURL") String overviewURL,
			@QueryParam("startTime") Date startTime,
			@QueryParam("endTime") Date endTime) {

		throwException(name, "name of an uddisla");
		throwException(version, "version");
		throwException(overviewURL, "overviewURL");
		throwException(startTime.toString(), "startTime");
		throwException(endTime.toString(), "endTime");
	}

	private void checkServiceParam(@QueryParam("name") String name,
			@QueryParam("serviceName") String serviceName) {

		throwException(name, "name of a service term");
		throwException(serviceName, "serviceName of a service term");
	}

	private void checkGuaranteeParam(
			@QueryParam("serviceName") String serviceName,
			@QueryParam("timeInterval") String timeInterval,
			@QueryParam("count") int count,
			@QueryParam("valueUnit") float valueUnit) {
		throwException(serviceName, "serviceName of a guarantee term");
		throwException(timeInterval, "timeInterval of a reward");
		throwException(String.valueOf(count), "count of a reward");
		throwException(String.valueOf(valueUnit), "valueUnit of a reward");
	}

	private void checkKPIParam(@QueryParam("name") String name,
			@QueryParam("qualifyingCondiction") String qualifyingCondiction,
			@QueryParam("targetValue") String targetValue) {

		throwException(name, "name of a Key Performance Indicator");
		throwException(qualifyingCondiction,
				"qualifyingCondiction of a Key Performance Indicator");
		throwException(targetValue,
				"targetValue of a Key Performance Indicator");

	}

	private void throwException(String value, String element) {

		if (value.isEmpty() || value == null) {
			ResponseHelp.BAD_REQUEST_MESSAGE += "'" + element
					+ "' is mandatory.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}
	}
}
