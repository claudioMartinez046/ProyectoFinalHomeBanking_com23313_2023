package com.ar.bankingonline.api.controllers.dtos;

import com.ar.bankingonline.domain.models.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransferDto {
    private Long id;
    private Long origin; // check
    private Long target; // check
    private Date date;
    private BigDecimal amount;
}
