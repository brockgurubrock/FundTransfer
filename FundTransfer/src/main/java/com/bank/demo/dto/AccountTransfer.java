package com.bank.demo.dto;

public class AccountTransfer {
	private Long fromAccountnumber;
	private Long toAccountnumber;
	public Long getFromAccountnumber() {
		return fromAccountnumber;
	}
	public void setFromAccountnumber(Long fromAccountnumber) {
		this.fromAccountnumber = fromAccountnumber;
	}
	public Long getToAccountnumber() {
		return toAccountnumber;
	}
	public void setToAccountnumber(Long toAccountnumber) {
		this.toAccountnumber = toAccountnumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	private double amount;
	
	
	

}
