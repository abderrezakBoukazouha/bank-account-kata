package org.example.services;

import org.example.entities.Account;
import org.example.entities.Transa;
import org.example.repositories.TransaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaService {

    @Autowired
    private TransaRepository transaRepository;

    public Account add (Account account, Transa transa) {
        List<Transa> currentTransactions = account.getTransas();
        currentTransactions.add(transa);
        account.setTransas(currentTransactions);
        transaRepository.save(transa);
        return account;
    }
}
