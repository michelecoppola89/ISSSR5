package com.isssr5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isssr5.entities.Scale;
import com.isssr5.persistence.ScaleDao;


@Service
public class ScaleTransactionImplementation implements ScaleTransaction{
	
	@Autowired
	private ScaleDao scaleDao;
	
	@Override
	@Transactional
	public void addScale(Scale scale) {
		scaleDao.addScale(scale);			
	}

	@Override
	@Transactional
	public void updateScale(Scale scale) {
		scaleDao.updateScale(scale);
		
	}

	@Override
	@Transactional
	public void deleteScale(Scale scale) {
		scaleDao.deleteScale(scale);
		
	}

	@Override
	@Transactional
	public Scale findScaleById(long idScale) {
		return scaleDao.findScaleById(idScale);
	}

}
