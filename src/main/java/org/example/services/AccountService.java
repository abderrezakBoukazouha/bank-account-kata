package org.example.services;

import org.example.dto.AccountDto;
import org.example.entities.Account;
import org.example.entities.Transa;
import org.example.enums.Operation;
import org.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import static java.time.Clock.systemUTC;
import static org.example.enums.Operation.*;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    TransaService transaService;

    public ResponseEntity<String> update(AccountDto accountDto, long id) {

        Account account = customerService.findBankUserById(id).getAccount();
        Operation operation = accountDto.getAmount() > 0 ? FUND : WITHDRAWAL;
        setNewAmount(accountDto.getAmount(), account);
        addCurrentOperation(account, operation, accountDto.getAmount());
        return response(account.getAmount());
    }

    public Account get(long id) {
        Account account = customerService.findBankUserById(id).getAccount();
        addCurrentOperation(account, CHECKED, 0);
        // TODO : Tri operations
        return account;
    }

    private void setNewAmount(double introducedAmount, Account account) {
        double currentAmount = account.getAmount();
        account.setAmount(currentAmount + introducedAmount);
    }

    private ResponseEntity<String> response(double amount) {
        return new ResponseEntity<String>("current amount in the bank " + amount + " $", HttpStatus.CREATED);
    }

    private void addCurrentOperation(Account account, Operation operation, double operationAmount) {
        Instant instant = Instant.now(systemUTC());
        double currentAmount = account.getAmount();
        Transa transa = Transa.builder()
                .instant(instant).operation(operation)
                .operationAmount(operationAmount).balance(currentAmount).account(account)
                .build();

        Account updateAccount = transaService.add(account, transa);
        accountRepository.save(updateAccount);
    }
}
