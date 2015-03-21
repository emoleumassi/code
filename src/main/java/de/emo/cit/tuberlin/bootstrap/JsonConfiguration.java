package de.emo.cit.tuberlin.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.emo.cit.tuberlin.json.SLAJson;
import de.emo.cit.tuberlin.json.ThesisJson;
import de.emo.cit.tuberlin.json.UDDIJson;
import de.emo.cit.tuberlin.json.UDDISLAJson;

@Configuration
public class JsonConfiguration {

	@Bean(name="slaJson")
	public SLAJson getSlaJson(){
		return new SLAJson();
	}

	@Bean(name="uddiJson")
	public UDDIJson getUDDIJson(){
		return new UDDIJson();
	}
	
	@Bean(name="uddiSlaJson")
	public UDDISLAJson getUDDISLAJson(){
		return getThesisJson().parseJSON();
	}
	
	@Bean(name = "thesisJson")
	public ThesisJson getThesisJson() {
		return new ThesisJson();
	}
}
