package com.bank.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.constants.ExceptionMsg;
import com.bank.demo.dto.AccountTransfer;
import com.bank.demo.dto.DepositForm;
import com.bank.demo.exception.AccountNotFoundException;
import com.bank.demo.exception.AmountNotSufficientException;
import com.bank.demo.service.AccountService;

@RestController
@RequestMapping(value = "/bank")
public class TransactionController {
	@Autowired
	private AccountService accountService;

	@PostMapping(path = "/account/bank")
	public ResponseEntity<Void> deposit(@RequestBody DepositForm form) {

		accountService.deposit(form.getAccountnumber(), form.getAmount());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping(path = "/account/account/bank")
	public ResponseEntity<Void> withdraw(@RequestBody DepositForm form) throws AmountNotSufficientException {

		accountService.withdraw(form.getAccountnumber(), form.getAmount());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping(path = "/account/fund")
	public ResponseEntity<Void> fundTransfer(@RequestBody AccountTransfer transfer) {

		accountService.fundTransfer(transfer.getFromAccountnumber(), transfer.getToAccountnumber(),
				transfer.getAmount());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(value = AmountNotSufficientException.class)
	public ResponseEntity<String> amountException(AccountNotFoundException ex) {
		return new ResponseEntity<>(ExceptionMsg.Amount_not_Sufficient, HttpStatus.NOT_FOUND);
	}

}
