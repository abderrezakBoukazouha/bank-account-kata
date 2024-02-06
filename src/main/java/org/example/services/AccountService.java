package org.example.services;

import org.example.dto.AccountDto;
import org.example.entities.Account;
import org.example.entities.Customer;
import org.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    customerService customerService;

    public ResponseEntity<String> update(AccountDto accountDto, long id) throws ChangeSetPersister.NotFoundException {

        Account account = customerService.findBankUserById(id).getAccount();
        setNewAmount(accountDto.getAmount(), account);
        return response(account.getAmount());
    }

    public ResponseEntity<String> get(long id) throws ChangeSetPersister.NotFoundException {
        Account account = customerService.findBankUserById(id).getAccount();
        return response(account.getAmount());

    }

    private void setNewAmount(double introducedAmount, Account account) {
        double currentAmount = account.getAmount();
        account.setAmount(currentAmount + introducedAmount);
        accountRepository.save(account);
    }

    private ResponseEntity<String> response (double amount) {
        return new ResponseEntity<String>("current amount in the bank " + amount + " $", HttpStatus.CREATED);
    }
}
