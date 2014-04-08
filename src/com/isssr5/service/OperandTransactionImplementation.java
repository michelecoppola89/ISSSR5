package com.isssr5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isssr5.entities.Operand;
import com.isssr5.persistence.OperandDao;

@Service
public class OperandTransactionImplementation implements OperandTransaction {
	
	@Autowired
	private OperandDao operandDao;

	@Override
	@Transactional
	public void addOperand(Operand op) {
		operandDao.addOperand(op);
		
	}

	@Override
	@Transactional
	public void updateOperand(Operand op) {
		operandDao.updateOperand(op);
		
	}

	@Override
	@Transactional
	public void deleteOperand(Operand op) {
		operandDao.deleteOperand(op);
		
	}

	@Override
	@Transactional
	public Operand findOperandById(long operandId) {
		return operandDao.findOperandById(operandId);
	}

}
