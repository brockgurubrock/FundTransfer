package com.bank.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.bank.demo.entities.Account;
import com.bank.demo.entities.AccountType;
import com.bank.demo.repository.AccountRepository;


public class RepositoryTest {
	private Account createAccount;
	private Account updateAccount;
	private Account deleteAccount;
	private Account readAccount;

	@BeforeEach
	public void setup() {
		createAccount = new Account("abc345", AccountType.SavingAccount, 40000, true);

	}

	@Mock
	private AccountRepository accountRepository;

	@Test
	public void CreateAccountTest() {
		/*
		 * accountRepository.save(createAccount);
		 * System.out.println(createAccount.getBalance()); accountRepository.flush();
		 * Account findByIfsc = (Account)
		 * accountRepository.findByIfsc(createAccount.getIfsc());
		 * assertThat(findByIfsc.getIfsc()).isEqualTo(createAccount.getIfsc());
		 */
		when(accountRepository.save(createAccount)).thenReturn(createAccount);

	}

}
