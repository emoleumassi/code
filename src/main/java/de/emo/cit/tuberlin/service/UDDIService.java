package de.emo.cit.tuberlin.service;

import java.util.List;

import de.emo.cit.tuberlin.model.UDDI;

public interface UDDIService {

	public List<UDDI> listUDDI();
	
	public UDDI getUDDIById(int id);
	
	public void createEntity(UDDI uddi);
}
