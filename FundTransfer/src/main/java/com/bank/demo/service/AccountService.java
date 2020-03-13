package com.bank.demo.service;

import java.util.List;

import com.bank.demo.dto.*;
import com.bank.demo.entities.Account;
import com.bank.demo.exception.AmountNotSufficientException;
public interface AccountService {
	public void createAccount(Account account);
	Account updateAccount(UpdateAccount req);
	public void deleteAccount(Long Accno);
	//retrive using ifsc
	public List<Account>retriveAccount(String ifsc);
	//retrive using accountno
	public Account AccountretriveAccountUsingAccountNo(Long accno);
	public void deposit(Long accountno,double amount);
	public void withdraw(Long accountno,double amount)throws AmountNotSufficientException;
	public void fundTransfer(Long accountno1,Long accountno2,double amount);

}
