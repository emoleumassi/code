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
import de.emo.cit.tuberlin.service.GetService;

/**
 * 
 * @author emoleumassi
 * 
 */
public class CheckJsonData {

	@SuppressWarnings("rawtypes")
	public CheckJsonData(ThesisRoot thesisRoot, GetService getService) {

		UDDISLA uddisla = thesisRoot.getUddisla();
		OverviewDoc overviewDoc = uddisla.getUddi().getOverviewDoc();
		SLA sla = uddisla.getSla();
		List<ServiceTerms> serviceTermsList = sla.getServiceTerms();
		List<GuaranteeTerms> guaranteeTermsList = sla.getGuaranteeTerms();

		checkPathParam(uddisla.getName(), uddisla.getVersion(),
				overviewDoc.getOverviewURL(), uddisla.getStartTime(),
				uddisla.getEndTime());

		String templateId = sla.getTemplateId();
		String templateName = sla.getTemplateName();
		if (!templateId.isEmpty())
			checkSLATemplate(templateId, getService);
		if (!templateName.isEmpty())
			checkSLATemplate(templateName, getService);

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
				if (serviceTerms.getServiceName().trim().toLowerCase()
						.equals(serviceName.trim().toLowerCase())) {
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

			KeyPerformanceIndicator kpi = guaranteeTerms
					.getKeyPerformanceIndicator();
			checkKPIParam(kpi.getAvailability(), kpi.getLatency(),
					kpi.getMttr(), kpi.getMtbf(), kpi.getResponseTime());
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

	private void checkKPIParam(@PathParam("availability") short availability,
			@PathParam("latency") short latency, @PathParam("mttr") short mttr,
			@PathParam("mtbf") short mtbf,
			@PathParam("responseTime") short responseTime) {

		if (availability == 0 && latency == 0 && mttr == 0 && mtbf == 0
				&& responseTime == 0) {
			ResponseHelp.BAD_REQUEST_MESSAGE += "Please give minimun one KeyPerformanceIndicator.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}

	}

	private void emptyException(String value, String element) {

		if (value.isEmpty() || value == null) {
			ResponseHelp.BAD_REQUEST_MESSAGE += "'" + element
					+ "' is mandatory.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkSLATemplate(String template, GetService getService) {

		List<UDDISLA> uddislas = getService.getUDDISLAByIdName(template);
		if (uddislas.isEmpty()) {
			ResponseHelp.BAD_REQUEST_MESSAGE += " There is not reference with "
					+ "the templateId or templateName: '" + template
					+ "' in the registry.\n";
			throw new ClientRequestException(ResponseHelp.BAD_REQUEST_STATUS,
					ResponseHelp.BAD_REQUEST_MESSAGE);
		}
	}
}
