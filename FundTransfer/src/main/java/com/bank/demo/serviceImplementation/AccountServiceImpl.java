package com.bank.demo.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bank.demo.dto.*;
import com.bank.demo.entities.Account;
import com.bank.demo.entities.Transaction;
import com.bank.demo.exception.AccountNotFoundException;
import com.bank.demo.exception.AmountNotSufficientException;
import com.bank.demo.repository.AccountRepository;
import com.bank.demo.repository.CustomerRepository;
import com.bank.demo.repository.TransactionRepository;
import com.bank.demo.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	private TransactionRepository transactionRepository;

	@Override
	public void createAccount(Account account) {
		accountRepository.save(account);
		customerRepository.save(account.getCustomer());

	}

	@Override
	public Account updateAccount(UpdateAccount req) {

		Account account = accountRepository.findById(req.getAccNo()).orElseThrow(AccountNotFoundException::new);
		account.setAccountStatus(req.getAccountStatus());
		accountRepository.save(account);
		return account;
	}

	@Override
	public void deleteAccount(Long Accno) {
		Account account = accountRepository.findById(Accno).orElseThrow(AccountNotFoundException::new);
		accountRepository.delete(account);
	}

	@Override
	public List<Account> retriveAccount(String ifsc) {

		List<Account> account = accountRepository.findByIfsc(ifsc);
		return account;
	}

	@Override
	public Account AccountretriveAccountUsingAccountNo(Long accno) {
		Account accounts = accountRepository.findById(accno).orElseThrow(AccountNotFoundException::new);
		return accounts;
	}

	@Override
	public void deposit(Long accountno, double amount) {
		Account account = accountRepository.findById(accountno).orElseThrow(AccountNotFoundException::new);
		
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);

	}

	@Override
	public void withdraw(Long accountno, double amount) throws AmountNotSufficientException {

		Account account = accountRepository.findById(accountno).orElseThrow(AccountNotFoundException::new);
		if(account.getBalance()>500)
		{
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
		}
		else
		{
			throw new AmountNotSufficientException();
		}
	}

	@Override
	public void fundTransfer(Long accountno1, Long accountno2, double amount) {
		/*
		 * this.withdraw(accountno1, amount); this.deposit(accountno2, amount);
		 * Transaction transaction = new Transaction(accountno2, "fundTransfer", amount,
		 * accountno1); transactionRepository.save(transaction);
		 * 
		 */
		Account account1 = accountRepository.findById(accountno1).orElseThrow(AccountNotFoundException::new);
		account1.setBalance(account1.getBalance() - amount);
		accountRepository.save(account1);
		Account account2 = accountRepository.findById(accountno2).orElseThrow(AccountNotFoundException::new);
		account2.setBalance(account2.getBalance() + amount);
		accountRepository.save(account2);
		Transaction transaction = new Transaction(accountno2, "fundTransfer", amount, accountno1);
		transactionRepository.save(transaction);

	}

}
