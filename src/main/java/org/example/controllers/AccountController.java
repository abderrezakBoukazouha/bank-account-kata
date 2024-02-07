package org.example.controllers;

import org.example.dto.AccountDto;
import org.example.entities.Account;
import org.example.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("api/v1/deposit/{id}")
    public ResponseEntity<String> updateAccount(@RequestBody AccountDto accountDto, @PathVariable long id) {

        return accountService.update(accountDto, id);
    }
    @GetMapping("api/v1/operations/{id}")
    public Account getOperations (@PathVariable long id) {
        return accountService.get(id);
    }

}
