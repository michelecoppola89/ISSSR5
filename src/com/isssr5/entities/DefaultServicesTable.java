package com.isssr5.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultServicesTable {

	private static DefaultServicesTable instance = null;

	private HashMap<String, MacroService> table;

	private DefaultServicesTable() {

		table = new HashMap<String, MacroService>();

		MacroService avg = new MacroService("AVG", null, null);
		avg.setNumOperand(1);
		avg.setDescription("Calculate mean");
		ArrayList<String> avg_key = new ArrayList<String>();
		avg_key.add("mean");
		avg.setKeywords(avg_key);
		table.put(avg.getIdCode(), avg);

		MacroService var = new MacroService("VAR", null, null);
		var.setNumOperand(1);
		var.setDescription("Calculate variance");
		ArrayList<String> var_key = new ArrayList<String>();
		var_key.add("variance");
		var.setKeywords(var_key);
		table.put(var.getIdCode(), var);

		MacroService avgvar = new MacroService("AVGVAR", null, null);
		avgvar.setNumOperand(1);

		ArrayList<String> ti1 = new ArrayList<String>();
		ti1.add("AVG");
		ti1.add("VAR");
		avgvar.setElementaryServices(ti1);
		avgvar.setDescription("Calculate mean and variance");
		ArrayList<String> avgvar_key = new ArrayList<String>();
		avgvar_key.add("variance");
		avgvar_key.add("mean");
		avgvar.setKeywords(avgvar_key);

		ArrayList<Integer> ti2 = new ArrayList<Integer>();
		ti2.add(1);
		ParameterList pl1 = new ParameterList(ti2);
		ArrayList<Integer> ti3 = new ArrayList<Integer>();
		ti3.add(1);
		ParameterList pl2 = new ParameterList(ti3);
		ArrayList<ParameterList> ti4 = new ArrayList<ParameterList>();
		ti4.add(pl1);
		ti4.add(pl2);
		avgvar.setOperationOrder(ti4);

		table.put(avgvar.getIdCode(), avgvar);

	}

	public static DefaultServicesTable getInstance() {
		if (instance == null) {
			instance = new DefaultServicesTable();
		}
		return instance;
	}

	public HashMap<String, MacroService> getTable() {
		return table;
	}

	public void setTable(HashMap<String, MacroService> table) {
		this.table = table;
	}

}
