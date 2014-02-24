package com.isssr5.entities;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ScaleFactoryBean implements FactoryBean<Scale>, InitializingBean {
	
	private Scale scale;
	private String type;
	private String domain;

	@Override
	public Scale getObject() throws Exception {
		// TODO Auto-generated method stub
		return this.scale;
	}

	@Override
	public Class<Scale> getObjectType() {
		// TODO Auto-generated method stub
		return Scale.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.scale= ScaleFactory.getScaleInstance(this.type, this.domain);
		
		
	}
	
	

}
