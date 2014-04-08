package com.isssr5.persistence;

import com.isssr5.entities.Operand;

public interface OperandDao {
	
	public void addOperand(Operand op);

	public void updateOperand(Operand op);

	public void deleteOperand(Operand op);
	
	public Operand findOperandById(long operandId);

}
