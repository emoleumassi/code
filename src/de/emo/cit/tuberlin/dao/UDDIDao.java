package de.emo.cit.tuberlin.dao;

import java.util.List;

import de.emo.cit.tuberlin.model.UDDI;

public interface UDDIDao {

	public List<UDDI> listUDDI();
	
	public UDDI getUDDIById(int id);
}
