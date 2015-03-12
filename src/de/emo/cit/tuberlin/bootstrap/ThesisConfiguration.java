package de.emo.cit.tuberlin.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.emo.cit.tuberlin.Emo;
import de.emo.cit.tuberlin.Track;

@Configuration
public class ThesisConfiguration {

	@Bean
	public Emo emo(){
		return new Emo();
	}
	
	@Bean
	public Track track(){
		return new Track();
	}
}
