package com.isssr5.service;

import java.util.List;

import com.isssr5.entities.MacroService;

public interface MacroServiceTransaction {
	
	public void addMacroService(MacroService ms);

	public void updateMacroService(MacroService ms);

	public void deleteMacroService(MacroService ms);
	
	public MacroService findMacroServiceById(String macroServiceId);
	
	public List <MacroService> findPublicMacroService();
	
	public List <MacroService> findPrivateMacroServiceById(String UserId);


}
