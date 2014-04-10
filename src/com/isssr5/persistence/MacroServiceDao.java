package com.isssr5.persistence;

import com.isssr5.entities.MacroService;


public interface MacroServiceDao {
	
	public void addMacroService(MacroService ms);

	public void updateMacroService(MacroService ms);

	public void deleteMacroService(MacroService ms);
	
	public MacroService findMacroServiceById(String macroServiceId);


}
