package com.hcl.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hcl.bank.dto.BenificiaryDTO;
import com.hcl.bank.dto.BenificiaryRequest;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.Benificiary;
import com.hcl.bank.entity.Customer;
import com.hcl.bank.exception.AccountNotFoundException;
import com.hcl.bank.exception.BenficiaryAlreadyExcistException;
import com.hcl.bank.exception.BenificiaryNotFoundException;
import com.hcl.bank.exception.CustomerNotLoggedInException;
import com.hcl.bank.exception.UserNotFoundException;
import com.hcl.bank.repository.AccountRepository;
import com.hcl.bank.repository.BenificiaryRepository;
import com.hcl.bank.repository.CustomerRepository;
import com.hcl.bank.service.BenificiaryService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestBenificiary {
	@Mock
	private BenificiaryRepository benificiaryRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private CustomerService customerService;

	@InjectMocks
	private BenificiaryServiceImpl benificiaryServiceImpl;

	@Before
	public void setup() {

	}

	
	@Test
	public void addBenificiaryTest() {
		Account account = new Account();
		account.setAccountNumber(12345L);
		Customer customer = new Customer();
		customer.setCustomerId(2000L);
		customer.setIsLoggedIn(true);

		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(100L);
		benificiary.setBenificiaryAccountNumber(12345L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		BenificiaryRequest benificiaryRequest = new BenificiaryRequest();
		benificiaryRequest.setName("guru");
		benificiaryRequest.setAccountNumber(12345L);
		benificiaryRequest.setCustomerId(2000L);
		Mockito.when(accountRepository.findByAccountNumber(12345L)).thenReturn(Optional.of(account));
		Mockito.when(customerRepository.findById(2000L)).thenReturn(Optional.of(customer));
		Mockito.when(benificiaryRepository.findByUserIdAndAccountNumber(benificiaryRequest.getAccountNumber(),
				benificiaryRequest.getCustomerId())).thenReturn(Optional.empty());
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		// Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
		// .thenReturn(new Boolean(false));

		String response = benificiaryServiceImpl.addBenificiary(benificiaryRequest);
		assertEquals("Benificiary added successfully.", response);

	}

	@Test
	public void customerNotLoggedInExceptionBenificiaryTest() {

		Account account = new Account();
		account.setAccountNumber(12345L);
		Customer customer = new Customer();
		customer.setCustomerId(2000L);
		customer.setIsLoggedIn(false);

		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(100L);
		benificiary.setBenificiaryAccountNumber(12345L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		BenificiaryRequest benificiaryRequest = new BenificiaryRequest();
		benificiaryRequest.setName("guru");
		benificiaryRequest.setAccountNumber(12345L);
		benificiaryRequest.setCustomerId(2000L);
		Mockito.when(accountRepository.findByAccountNumber(12345L)).thenReturn(Optional.of(account));
		Mockito.when(customerRepository.findById(2000L)).thenReturn(Optional.of(customer));
		Mockito.when(benificiaryRepository.findByUserIdAndAccountNumber(benificiaryRequest.getAccountNumber(),
				benificiaryRequest.getCustomerId())).thenReturn(Optional.empty());
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		// Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
		// .thenReturn(new Boolean(false));

		assertThrows(CustomerNotLoggedInException.class, () -> {
			benificiaryServiceImpl.addBenificiary(benificiaryRequest);
		});

	}

	@Test
	public void accountNotFoundExceptionBenificiaryTest() {

		Account account = new Account();
		account.setAccountNumber(12345L);
		Customer customer = new Customer();
		customer.setCustomerId(2000L);
		customer.setIsLoggedIn(true);

		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(100L);
		benificiary.setBenificiaryAccountNumber(12345L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		BenificiaryRequest benificiaryRequest = new BenificiaryRequest();
		benificiaryRequest.setName("guru");
		benificiaryRequest.setAccountNumber(12345L);
		benificiaryRequest.setCustomerId(2000L);
		Mockito.when(accountRepository.findByAccountNumber(123456L)).thenReturn(Optional.of(account));
		Mockito.when(customerRepository.findById(2000L)).thenReturn(Optional.of(customer));
		Mockito.when(benificiaryRepository.findByUserIdAndAccountNumber(benificiaryRequest.getAccountNumber(),
				benificiaryRequest.getCustomerId())).thenReturn(Optional.empty());
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		// Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
		// .thenReturn(new Boolean(false));

		assertThrows(AccountNotFoundException.class, () -> {
			benificiaryServiceImpl.addBenificiary(benificiaryRequest);
		});

	}

	@Test
	public void userNotFoundExceptionBenificiaryTest() {

		Account account = new Account();
		account.setAccountNumber(12345L);
		Customer customer = new Customer();
		customer.setCustomerId(2000L);
		customer.setIsLoggedIn(true);

		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(100L);
		benificiary.setBenificiaryAccountNumber(12345L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		BenificiaryRequest benificiaryRequest = new BenificiaryRequest();
		benificiaryRequest.setName("guru");
		benificiaryRequest.setAccountNumber(12345L);
		benificiaryRequest.setCustomerId(2000L);
		Mockito.when(accountRepository.findByAccountNumber(12345L)).thenReturn(Optional.of(account));
		Mockito.when(customerRepository.findById(20001L)).thenReturn(Optional.of(customer));
		Mockito.when(benificiaryRepository.findByUserIdAndAccountNumber(benificiaryRequest.getAccountNumber(),
				benificiaryRequest.getCustomerId())).thenReturn(Optional.empty());
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		// Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
		// .thenReturn(new Boolean(false));

		assertThrows(UserNotFoundException.class, () -> {
			benificiaryServiceImpl.addBenificiary(benificiaryRequest);
		});

	}

	@Test
	public void benficiaryAlreadyExcistExceptionBenificiaryTest() {

		Account account = new Account();
		account.setAccountNumber(12345L);
		Customer customer = new Customer();
		customer.setCustomerId(2000L);
		customer.setIsLoggedIn(true);

		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(100L);
		benificiary.setBenificiaryAccountNumber(12345L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		BenificiaryRequest benificiaryRequest = new BenificiaryRequest();
		benificiaryRequest.setName("guru");
		benificiaryRequest.setAccountNumber(12345L);
		benificiaryRequest.setCustomerId(2000L);
		Mockito.when(accountRepository.findByAccountNumber(12345L)).thenReturn(Optional.of(account));
		Mockito.when(customerRepository.findById(2000L)).thenReturn(Optional.of(customer));
		Mockito.when(benificiaryRepository.findByUserIdAndAccountNumber(benificiaryRequest.getAccountNumber(),
				benificiaryRequest.getCustomerId())).thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		// Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
		// .thenReturn(new Boolean(false));

		assertThrows(BenficiaryAlreadyExcistException.class, () -> {
			benificiaryServiceImpl.addBenificiary(benificiaryRequest);
		});

	}

	@Test
	public void testUpdateBenificiary() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(true);
		Account account = new Account();
		account.setAccountNumber(2000L);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(1L);
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryName("vandna");
		benificiary.setCustomer(customer);

		BenificiaryDTO benificiaryRequest = new BenificiaryDTO();
		benificiaryRequest.setBenificiaryAccountNumber(2000L);
		benificiaryRequest.setBenificiaryName("guru");
		benificiaryRequest.setCustomerId(1000L);
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(customerRepository.findById(benificiaryRequest.getCustomerId())).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber(benificiaryRequest.getBenificiaryAccountNumber()))
				.thenReturn(Optional.of(account));
		Mockito.when(benificiaryRepository.findByCustomerId(benificiaryRequest.getCustomerId()))
				.thenReturn(benificiary);
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);
		String response = benificiaryServiceImpl.updatedBenificiary(benificiaryRequest);
		assertEquals("Benificiary updated successfully.", response);
	}

	@Test
	public void customerNotLoggedInExceptiontestUpdateBenificiary() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(false);
		Account account = new Account();
		account.setAccountNumber(2000L);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(1L);
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryName("vandna");
		benificiary.setCustomer(customer);

		BenificiaryDTO benificiaryRequest = new BenificiaryDTO();
		benificiaryRequest.setBenificiaryAccountNumber(2000L);
		benificiaryRequest.setBenificiaryName("guru");
		benificiaryRequest.setCustomerId(1000L);
		Mockito.when(customerService.checkLoggingStatus(benificiaryRequest.getCustomerId()))
				.thenReturn(customer.getIsLoggedIn());
		Mockito.when(customerRepository.findById(benificiaryRequest.getCustomerId())).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByAccountNumber(benificiaryRequest.getBenificiaryAccountNumber()))
				.thenReturn(Optional.of(account));
		Mockito.when(benificiaryRepository.findByCustomerId(benificiaryRequest.getCustomerId()))
				.thenReturn(benificiary);
		Mockito.when(benificiaryRepository.save(benificiary)).thenReturn(benificiary);

		assertThrows(CustomerNotLoggedInException.class, () -> {
			benificiaryServiceImpl.updatedBenificiary(benificiaryRequest);
		});
	}


	@Test
	public void testdeleteBeneficiary() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(true);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryId(1L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		Mockito.when(benificiaryRepository.findById(benificiary.getBenificiaryId()))
				.thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());
		benificiaryRepository.deleteById(benificiary.getBenificiaryId());
		String response = benificiaryServiceImpl.deleteBenificiary(1L);
		assertEquals("Benificiar Deleted Successfully", response);

	}
	@Test
	public void customerNotLoggedInExceptiontestdeleteBeneficiary() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(false);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryId(1L);
		benificiary.setBenificiaryName("guru");
		benificiary.setCustomer(customer);
		Mockito.when(benificiaryRepository.findById(benificiary.getBenificiaryId()))
				.thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());
		benificiaryRepository.deleteById(benificiary.getBenificiaryId());
	
		assertThrows(CustomerNotLoggedInException.class, () -> {
			 benificiaryServiceImpl.deleteBenificiary(1L);
		});
	}
		@Test
		public void benificiaryNotFoundExceptiontestdeleteBeneficiary() {
			Customer customer = new Customer();
			customer.setCustomerId(1000L);
			customer.setIsLoggedIn(false);
			Benificiary benificiary = new Benificiary();
			benificiary.setBenificiaryAccountNumber(2000L);
			benificiary.setBenificiaryId(1L);
			benificiary.setBenificiaryName("guru");
			benificiary.setCustomer(customer);
			Mockito.when(benificiaryRepository.findById(benificiary.getBenificiaryId()))
					.thenReturn(Optional.of(benificiary));
			Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());
			benificiaryRepository.deleteById(benificiary.getBenificiaryId());
		
			assertThrows(BenificiaryNotFoundException.class, () -> {
				 benificiaryServiceImpl.deleteBenificiary(2L);
			});

			
	}
	

	@Test
	public void retriveBenificiaryTest() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(true);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(10001L);
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryName("chandru");
		benificiary.setCustomer(customer);
		BenificiaryDTO benificiaryDTO = new BenificiaryDTO();

		benificiaryDTO.setBenificiaryAccountNumber(benificiary.getBenificiaryAccountNumber());
		benificiaryDTO.setBenificiaryName(benificiary.getBenificiaryName());
		benificiaryDTO.setCustomerId(benificiary.getCustomer().getCustomerId());

		Mockito.when(benificiaryRepository.findById(10001L)).thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());
		BenificiaryDTO response = benificiaryServiceImpl.getBenificiaryByBenificiaryId(10001L);
		assertEquals(response.getBenificiaryName(), "chandru");
	}

	@Test
	public void customerNotLoggedInExceptionretriveBenificiaryTest() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(false);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(10001L);
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryName("chandru");
		benificiary.setCustomer(customer);
		BenificiaryDTO benificiaryDTO = new BenificiaryDTO();

		benificiaryDTO.setBenificiaryAccountNumber(benificiary.getBenificiaryAccountNumber());
		benificiaryDTO.setBenificiaryName(benificiary.getBenificiaryName());
		benificiaryDTO.setCustomerId(benificiary.getCustomer().getCustomerId());

		Mockito.when(benificiaryRepository.findById(10001L)).thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());

		assertThrows(CustomerNotLoggedInException.class, () -> {
			benificiaryServiceImpl.getBenificiaryByBenificiaryId(10001L);
		});
	}

	@Test
	public void benificiaryNotFoundExceptionretriveBenificiaryTest() {
		Customer customer = new Customer();
		customer.setCustomerId(1000L);
		customer.setIsLoggedIn(true);
		Benificiary benificiary = new Benificiary();
		benificiary.setBenificiaryId(10001L);
		benificiary.setBenificiaryAccountNumber(2000L);
		benificiary.setBenificiaryName("chandru");
		benificiary.setCustomer(customer);
		BenificiaryDTO benificiaryDTO = new BenificiaryDTO();

		benificiaryDTO.setBenificiaryAccountNumber(benificiary.getBenificiaryAccountNumber());
		benificiaryDTO.setBenificiaryName(benificiary.getBenificiaryName());
		benificiaryDTO.setCustomerId(benificiary.getCustomer().getCustomerId());

		Mockito.when(benificiaryRepository.findById(10001L)).thenReturn(Optional.of(benificiary));
		Mockito.when(customerService.checkLoggingStatus(1000L)).thenReturn(customer.getIsLoggedIn());

		assertThrows(BenificiaryNotFoundException.class, () -> {
			benificiaryServiceImpl.getBenificiaryByBenificiaryId(10002L);
		});
	}


}
