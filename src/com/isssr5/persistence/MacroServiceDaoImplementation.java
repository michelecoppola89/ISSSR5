package com.isssr5.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isssr5.entities.MacroService;

@Repository
public class MacroServiceDaoImplementation implements MacroServiceDao {

	private SessionFactory sessionFactory;

	@Autowired
	public MacroServiceDaoImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addMacroService(MacroService ms) {
		sessionFactory.getCurrentSession().save(ms);

	}

	@Override
	public void updateMacroService(MacroService ms) {
		sessionFactory.getCurrentSession().update(ms);

	}

	@Override
	public void deleteMacroService(MacroService ms) {
		sessionFactory.getCurrentSession().delete(ms);

	}

	@Override
	public MacroService findMacroServiceById(String macroServiceId) {

		return (MacroService) sessionFactory.getCurrentSession().get(
				MacroService.class, macroServiceId);
	}

}
