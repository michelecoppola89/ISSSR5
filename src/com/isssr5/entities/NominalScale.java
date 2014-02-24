package com.isssr5.entities;

import java.util.ArrayList;

public class NominalScale implements Scale {
	
	private String type;
	private ArrayList<String> scalePointList;
	
	public NominalScale(String type, String domain){
		System.out.println("ho creato");
		this.type=type;
		scalePointList = new ArrayList<String>();
		scalePointList.add(domain);
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		System.out.println(type);
		return this.type;
	}

	@Override
	public String getDomain() {
		// TODO Auto-generated method stub
		return scalePointList.toString();
	}

	@Override
	public void changeDomain(String domain) {
		// TODO Auto-generated method stub
		

	}

}
