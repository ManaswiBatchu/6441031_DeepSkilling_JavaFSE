package com.cognizant.account.controller;

import com.cognizant.account.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping(value = "/account", produces = "application/json")
    public Account getAccountDetails() {
        Account account = new Account();
        account.setAccountNumber(123456789);
       account.setAccountHolderName("John Doe");
        account.setAccountBalance(1000.0);
        return account;
    }
}
