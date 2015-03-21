package de.emo.cit.tuberlin.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.emo.cit.tuberlin.model.OverviewDoc;
import de.emo.cit.tuberlin.model.SLA;
import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;

@Configuration
public class ModelConfiguration {

	@Bean(name = "uddi")
	public UDDI getUDDI() {
		return new UDDI();
	}

	@Bean(name = "uddisla")
	public UDDISLA getUddiSla() {
		return new UDDISLA();
	}

	@Bean(name = "overviewDoc")
	public OverviewDoc getOverviewDoc() {
		return new OverviewDoc();
	}

	@Bean(name = "sla")
	public SLA getSLA() {
		return new SLA();
	}
}
