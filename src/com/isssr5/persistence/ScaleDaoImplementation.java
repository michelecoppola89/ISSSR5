package com.isssr5.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isssr5.entities.Scale;

@Repository
public class ScaleDaoImplementation implements ScaleDao {
	private SessionFactory sessionFactory;

	@Autowired
	public ScaleDaoImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addScale(Scale scale) {
		sessionFactory.getCurrentSession().save(scale);

	}

	@Override
	public void updateScale(Scale scale) {
		sessionFactory.getCurrentSession().update(scale);

	}

	@Override
	public void deleteScale(Scale scale) {
		sessionFactory.getCurrentSession().delete(scale);

	}

	@Override
	public Scale findScaleById(long idScale) {
		
		return (Scale) sessionFactory.getCurrentSession().get(Scale.class, idScale);
	}

}
