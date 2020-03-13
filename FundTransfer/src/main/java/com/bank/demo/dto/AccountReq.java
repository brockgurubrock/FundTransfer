package com.bank.demo.dto;

import com.bank.demo.entities.AccountType;
import com.bank.demo.entities.Customer;

public class AccountReq {
	private String ifsc;
	private AccountType accountType;
	private double balance;
	private Boolean accountStatus;
	private String name;
	private String address;
	private String contactno;
	private String email;
	private Boolean isEligible;
	

	
	/*
	 * public AccountReq(String ifsc, AccountType accountType, double balance,
	 * Boolean accountStatus, String name, String address, String contactno, String
	 * email, Boolean isEligible) {
	 * 
	 * this.ifsc = ifsc; this.accountType = accountType; this.balance = balance;
	 * this.accountStatus = accountStatus; this.name = name; this.address = address;
	 * this.contactno = contactno; this.email = email; this.isEligible = isEligible;
	 * }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsEligible() {
		return isEligible;
	}

	public void setIsEligible(Boolean isEligible) {
		this.isEligible = isEligible;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Boolean getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	

}
