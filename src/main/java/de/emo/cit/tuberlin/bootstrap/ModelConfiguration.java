package de.emo.cit.tuberlin.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.emo.cit.tuberlin.model.UDDI;
import de.emo.cit.tuberlin.model.UDDISLA;

@Configuration
public class ModelConfiguration {

	@Bean(name="uddi")
	public UDDI getUDDI(){
		return new UDDI();
	}
	
	@Bean(name="uddisla")
	public UDDISLA getUddiSla(){
		return new UDDISLA();
	}
}
