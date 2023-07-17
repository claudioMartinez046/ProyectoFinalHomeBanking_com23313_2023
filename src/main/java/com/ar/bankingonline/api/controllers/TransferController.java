package com.ar.bankingonline.api.controllers;

import com.ar.bankingonline.aplication.service.AccountService;
import com.ar.bankingonline.aplication.service.TransferServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransferController {
    private TransferServices service;

    @Autowired
    public TransferController(TransferServices service){
        this.service = service;
    }
    //completar
}
