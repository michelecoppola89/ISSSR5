package com.isssr5.service;

import com.isssr5.entities.Scale;

public interface ScaleTransaction {
	public void addScale(Scale scale);

	public void updateScale(Scale scale);

	public void deleteScale(Scale scale);

	public Scale findScaleById(long idScale);

}
