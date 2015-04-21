package de.emo.cit.tuberlin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.emo.cit.tuberlin.help.ThesisHelp;
import de.emo.cit.tuberlin.model.GuaranteeTerms;
import de.emo.cit.tuberlin.model.KeyPerformanceIndicator;
import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.Reward;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.ServiceTerms;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;
import de.emo.cit.tuberlin.service.PostService;
import de.emo.cit.tuberlin.service.ThesisService;

/**
 * 
 * @author emoleumassi
 * 
 */
@Service
public class PostServiceImpl implements PostService {

	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(PostServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	ThesisService thesisServive;

	private SLA sla;
	private UDDI uddi;
	private UDDISLA uddisla;
	private OverviewDoc overviewDoc;
	private List<ServiceTerms> serviceTermsList;
	private List<GuaranteeTerms> guaranteeTermsList;
	private List<KeyPerformanceIndicator> kpiList;

	@Override
	public void createServices(ThesisRoot thesisRoot) {

		uddisla = thesisRoot.getUddisla();
		uddisla.setUddislaId(ThesisHelp.newUUID());

		uddi = uddisla.getUddi();
		uddi.setUddiId(ThesisHelp.newUUID());

		sla = uddisla.getSla();
		sla.setSlaId(ThesisHelp.newUUID());

		overviewDoc = uddi.getOverviewDoc();
		overviewDoc.setOverviewDocId(ThesisHelp.newUUID());

		serviceTermsList = sla.getServiceTerms();
		guaranteeTermsList = sla.getGuaranteeTerms();
		for (ServiceTerms serviceTerms : serviceTermsList)
			serviceTerms.setServiceTermId(ThesisHelp.newUUID());

		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {
			guaranteeTerms.setGuaranteeTermId(ThesisHelp.newUUID());
			Reward reward = guaranteeTerms.getReward();
			reward.setRewardId(ThesisHelp.newUUID());
			kpiList = guaranteeTerms.getKeyPerformanceIndicator();
			for (KeyPerformanceIndicator kpi : kpiList)
				kpi.setKeyPerformanceIndicatorId(ThesisHelp.newUUID());
		}

		setEntity(uddisla);
		// LOGGER.info("succefull insert all the data!!!");

		updateEntities();

	}

	@SuppressWarnings("unchecked")
	private void setEntity(Object object) {
		thesisServive.setClazz(Object.class);
		thesisServive.createEntity(object);
	}

	private void updateEntities() {
		thesisServive.updateColumnById("SLA", "uddislaId", "slaId",
				uddisla.getUddislaId(), sla.getSlaId());

		thesisServive.updateColumnById("UDDI", "uddislaId", "uddiId",
				uddisla.getUddislaId(), uddi.getUddiId());

		thesisServive.updateColumnById("OverviewDoc", "uddiId",
				"overviewDocId", uddi.getUddiId(),
				overviewDoc.getOverviewDocId());

		for (ServiceTerms serviceTerms : serviceTermsList)
			thesisServive.updateColumnById("ServiceTerms", "slaId",
					"serviceTermId", sla.getSlaId(),
					serviceTerms.getServiceTermId());

		for (GuaranteeTerms guaranteeTerms : guaranteeTermsList) {
			thesisServive.updateColumnById("GuaranteeTerms", "slaId",
					"guaranteeTermId", sla.getSlaId(),
					guaranteeTerms.getGuaranteeTermId());
			thesisServive.updateColumnById("Reward", "guaranteeTermId",
					"rewardId", guaranteeTerms.getGuaranteeTermId(),
					guaranteeTerms.getReward().getRewardId());
			kpiList = guaranteeTerms.getKeyPerformanceIndicator();
			for (KeyPerformanceIndicator kpi : kpiList)
				thesisServive.updateColumnById("KeyPerformanceIndicator",
						"guaranteeTermId", "keyPerformanceIndicatorId",
						guaranteeTerms.getGuaranteeTermId(),
						kpi.getKeyPerformanceIndicatorId());
		}

		// LOGGER.info("succefull update all the foreign key!!!");
	}
}
