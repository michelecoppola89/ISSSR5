package com.isssr5.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultServicesTable {
	
	private HashMap<String, MacroService> table;
	
	public DefaultServicesTable(){
		
		table= new HashMap<String, MacroService>();
		
		MacroService avg=new MacroService("AVG",null,null);
		avg.setNumOperand(1);
		table.put(avg.getIdCode(), avg);
		
		
		MacroService var=new MacroService("VAR",null,null);
		var.setNumOperand(1);
		table.put(var.getIdCode(), var);
		
		MacroService avgvar=new MacroService("AVGVAR",null,null);
		avgvar.setNumOperand(1);
		
		ArrayList<MacroService> ti1= new ArrayList<MacroService>();
		ti1.add(avg);
		ti1.add(var);
		avgvar.setElementaryServices(ti1);
		
		ArrayList<Integer> ti2 = new ArrayList<Integer>();
		ti2.add(1);
		ParameterList pl1= new ParameterList(ti2);
		ArrayList<Integer> ti3 = new ArrayList<Integer>();
		ti3.add(1);
		ParameterList pl2= new ParameterList(ti3);
		ArrayList<ParameterList> ti4=new ArrayList<ParameterList>();
		ti4.add(pl1);
		ti4.add(pl2);
		avgvar.setOperationOrder(ti4);
		
		table.put(avgvar.getIdCode(), avgvar);
		

	}

	public HashMap<String, MacroService> getTable() {
		return table;
	}

	public void setTable(HashMap<String, MacroService> table) {
		this.table = table;
	}
	
	

}
