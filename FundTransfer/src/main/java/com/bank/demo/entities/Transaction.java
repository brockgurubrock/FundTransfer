package com.bank.demo.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transrefno;
	private Long benificiaryAccno;
	private String transactionType;
	private LocalDate transaction_Date;
	private double TranferAmount;
	private Long fromAccountNo;

	@ManyToOne
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	

	public Transaction(Long benificiaryAccno, String transactionType,double tranferAmount,
			Long fromAccountNo) {
		
		this.benificiaryAccno = benificiaryAccno;
		this.transactionType = transactionType;
		this.transaction_Date = LocalDate.now();
		TranferAmount = tranferAmount;
		this.fromAccountNo = fromAccountNo;
		
	}

	public Long getTransrefno() {
		return transrefno;
	}

	public void setTransrefno(Long transrefno) {
		this.transrefno = transrefno;
	}

	public Long getBenificiaryAccno() {
		return benificiaryAccno;
	}

	public void setBenificiaryAccno(Long benificiaryAccno) {
		this.benificiaryAccno = benificiaryAccno;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransaction_Date() {
		return transaction_Date;
	}

	public void setTransaction_Date(LocalDate transaction_Date) {
		this.transaction_Date = transaction_Date;
	}

	

	public double getTranferAmount() {
		return TranferAmount;
	}

	public void setTranferAmount(double tranferAmount) {
		TranferAmount = tranferAmount;
	}

	public Long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(Long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	

	public Transaction() {

	}

}
