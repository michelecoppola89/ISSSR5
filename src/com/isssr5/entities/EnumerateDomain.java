package com.isssr5.entities;

import java.util.ArrayList;

public class EnumerateDomain extends Domain {
public ArrayList<String> scalePoint;
	@Override
	public String getType() {
		return "enumerateDomain";
	}
	public ArrayList<String> getScalePoint() {
		return scalePoint;
	}
	public void setScalePoint(ArrayList<String> scalePoint) {
		this.scalePoint = scalePoint;
	}

}
