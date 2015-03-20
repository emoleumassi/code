package de.emo.cit.tuberlin.config;

import java.util.UUID;

public class GenerateUUID {

	private final static UUID createUUID(){
		return UUID.randomUUID();
	}
	
	public static final String newUUID(){
		return String.valueOf(createUUID());
	}
}
