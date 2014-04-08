package com.isssr5.service;

import com.isssr5.entities.Operand;

public interface OperandTransaction {
	
	public void addOperand(Operand op);

	public void updateOperand(Operand op);

	public void deleteOperand(Operand op);
	
	public Operand findOperandById(long operandId);

}
