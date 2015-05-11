package de.emo.cit.tuberlin.help;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ServiceTerms;
import de.emo.cit.tuberlin.model.UDDISLA;

/**
 * 
 * @author ferdinand
 *
 */
@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class HelpController {

	public List<UDDISLA> sortKPI(List<UDDISLA> uddislas,
			MultivaluedMap<String, String> hashMap, String serviceName) {

		List<UDDISLA> uddislaTmp = new LinkedList(uddislas);

		for (UDDISLA uddisla : uddislas) {
			int count = 0;
			SLA sla = uddisla.getSla();
			List<ServiceTerms> serviceList = sla.getServiceTerms();
			List<GuaranteeTerms> guaranteeList = sla.getGuaranteeTerms();

			List<ServiceTerms> serviceTmp = new LinkedList(serviceList);
			List<GuaranteeTerms> guaranteeTmp = new LinkedList(guaranteeList);

			for (int i = 0; i < serviceList.size(); i++) {

				boolean nothing = false;
				ServiceTerms serviceTerms = serviceList.get(i);
				GuaranteeTerms guaranteeTerms = guaranteeList.get(i);

				KeyPerformanceIndicator kpi = guaranteeTerms
						.getKeyPerformanceIndicator();

				boolean contains = guaranteeTerms.getServiceName()
						.toLowerCase().contains(serviceName.toLowerCase());
				if (!contains) {
					serviceTmp.remove(serviceTerms);
					guaranteeTmp.remove(guaranteeTerms);
					sla.setServiceTerms(serviceTmp);
					sla.setGuaranteeTerms(guaranteeTmp);
					uddisla.setSla(sla);
					continue;
				}

				if (!Objects.isNull(hashMap)) {
					for (Entry<String, List<String>> entry : hashMap.entrySet()) {

						String element = entry.getKey().toLowerCase().trim();
						short value = Short.valueOf(entry.getValue().get(0));

						if (element.equals("latency") && kpi.getLatency() != 0
								&& value >= kpi.getLatency())
							count++;
						else if (element.equals("mttr") && kpi.getMttr() != 0
								&& value >= kpi.getMttr())
							count++;
						else if (element.equals("responsetime")
								&& kpi.getResponseTime() != 0
								&& value >= kpi.getResponseTime())
							count++;
						else if (element.equals("availability")
								&& kpi.getAvailability() != 0
								&& value <= kpi.getAvailability())
							count++;
						else if (element.equals("mtbf") && kpi.getMtbf() != 0
								&& value <= kpi.getMtbf())
							count++;
						else
							nothing = true;
					}
					if (nothing || hashMap.size() > count) {
						serviceTmp.remove(serviceTerms);
						guaranteeTmp.remove(guaranteeTerms);
						if (guaranteeTmp.size() == 0 && serviceTmp.size() == 0) {
							uddislaTmp.remove(uddisla);
						} else {
							sla.setServiceTerms(serviceTmp);
							sla.setGuaranteeTerms(guaranteeTmp);
							uddisla.setSla(sla);
						}
					}
				}
			}
		}
		return uddislaTmp;
	}

	public Response setResponse(Object element) {
		if ((element instanceof List && ((List) element).isEmpty())
				|| Objects.isNull(element))
			return ResponseHelp.currentResponse(ResponseHelp.NOT_FOUND,
					ResponseHelp.NOT_FOUND_MESSAGE);
		else
			return ResponseHelp.currentResponse(ResponseHelp.OK, element);
	}
}
