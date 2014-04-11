package com.isssr5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isssr5.entities.MacroService;
import com.isssr5.persistence.MacroServiceDao;


@Service
public class MacroServiceTransactionImplementation implements MacroServiceTransaction {

	@Autowired
	private MacroServiceDao macroServiceDao;
	
	@Override
	@Transactional
	public void addMacroService(MacroService ms) {
		macroServiceDao.addMacroService(ms);
		
	}

	@Override
	@Transactional
	public void updateMacroService(MacroService ms) {
		macroServiceDao.updateMacroService(ms);
		
	}

	@Override
	@Transactional
	public void deleteMacroService(MacroService ms) {
		macroServiceDao.deleteMacroService(ms);
		
	}

	@Override
	@Transactional
	public MacroService findMacroServiceById(String macroServiceId) {
		return macroServiceDao.findMacroServiceById(macroServiceId);
	}

	@Override
	@Transactional
	public List<MacroService> findPublicMacroService() {
		return macroServiceDao.findPublicMacroService();
	}

	@Override
	@Transactional
	public List<MacroService> findPrivateMacroServiceById(String UserId) {
		return macroServiceDao.findPrivateMacroServiceById(UserId);
	}

}
