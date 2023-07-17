package com.ar.bankingonline.aplication.service;

import com.ar.bankingonline.infrastructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServices {
    @Autowired
    private TransferServices repository;

    public TransferServices(TransferServices repository) {
        this.repository = repository;
    }
}
