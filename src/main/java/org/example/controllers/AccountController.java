package org.example.controllers;

import org.example.dto.AccountDto;
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
    public ResponseEntity<String> depositAmount(@RequestBody AccountDto accountDto, @PathVariable long id)
            throws ChangeSetPersister.NotFoundException {

        return accountService.update(accountDto, id);
    }

    @GetMapping("api/v1/deposit/{id}")
    public ResponseEntity<String> currentAmount(@PathVariable long id)
            throws ChangeSetPersister.NotFoundException {

        return accountService.get( id);
    }

}
