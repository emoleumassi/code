package de.emo.cit.tuberlin.help;

import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;
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

		checkPathParam(uddisla.getName(), uddisla.getVersion(),
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

	private void checkPathParam(@PathParam("name") String name,
			@PathParam("version") String version,
			@PathParam("overviewURL") String overviewURL,
			@PathParam("startTime") Date startTime,
			@PathParam("endTime") Date endTime) {

		emptyException(name, "name of an uddisla");
		emptyException(version, "version");
		emptyException(overviewURL, "overviewURL");
		emptyException(startTime.toString(), "startTime");
		emptyException(endTime.toString(), "endTime");
	}

	private void checkServiceParam(@PathParam("name") String name,
			@PathParam("serviceName") String serviceName) {

		emptyException(name, "name of a service term");
		emptyException(serviceName, "serviceName of a service term");
	}

	private void checkGuaranteeParam(
			@PathParam("serviceName") String serviceName,
			@PathParam("timeInterval") String timeInterval,
			@QueryParam("count") int count,
			@PathParam("valueUnit") float valueUnit) {
		emptyException(serviceName, "serviceName of a guarantee term");
		emptyException(timeInterval, "timeInterval of a reward");
		emptyException(String.valueOf(count), "count of a reward");
		emptyException(String.valueOf(valueUnit), "valueUnit of a reward");
	}

	private void checkKPIParam(@PathParam("name") String name,
			@PathParam("qualifyingCondiction") short qualifyingCondiction,
			@PathParam("targetValue") short targetValue) {

		emptyException(name, "name of a Key Performance Indicator");
		emptyException(String.valueOf(qualifyingCondiction),
				"qualifyingCondiction of a Key Performance Indicator");
		emptyException(String.valueOf(targetValue),
				"targetValue of a Key Performance Indicator");

	}

	private void emptyException(String value, String element) {

		if (value.isEmpty() || value == null) {
			ResponseHelp.BAD_REQUEST_MESSAGE += "'" + element
					+ "' is mandatory.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}
	}
}
