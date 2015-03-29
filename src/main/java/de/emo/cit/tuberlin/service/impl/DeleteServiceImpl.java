package de.emo.cit.tuberlin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.emo.cit.tuberlin.service.DeleteService;

/**
 * 
 * @author emoleumassi
 *
 */
@Transactional
@Service("deleteService")
public class DeleteServiceImpl implements DeleteService{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteServiceImpl.class);
}
