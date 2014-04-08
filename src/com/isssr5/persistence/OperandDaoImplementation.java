package com.isssr5.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isssr5.entities.Operand;

@Repository
public class OperandDaoImplementation implements OperandDao {

	private SessionFactory sessionFactory;

	@Autowired
	public OperandDaoImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addOperand(Operand op) {
		sessionFactory.getCurrentSession().save(op);

	}

	@Override
	public void updateOperand(Operand op) {
		sessionFactory.getCurrentSession().update(op);

	}

	@Override
	public void deleteOperand(Operand op) {
		sessionFactory.getCurrentSession().delete(op);

	}

	@Override
	public Operand findOperandById(long operandId) {
		return (Operand) sessionFactory.getCurrentSession().get(Operand.class,
				operandId);

	}

}
