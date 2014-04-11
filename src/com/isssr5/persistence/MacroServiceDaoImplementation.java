package com.isssr5.persistence;

import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MacroService> findPublicMacroService() {
		String hql="FROM MacroService WHERE is_private = :isprivate";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isprivate", false);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MacroService> findPrivateMacroServiceById(String UserId) {
		String hql="FROM MacroService WHERE is_private = :isprivate AND user.userid= :UserId";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isprivate", true);
		query.setParameter("UserId", UserId);
		
		return query.list();
	}

}
