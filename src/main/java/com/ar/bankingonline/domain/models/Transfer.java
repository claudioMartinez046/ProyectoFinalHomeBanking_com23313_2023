package com.ar.bankingonline.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "origin_account_id", nullable = false)
    private Long origin;

    //@ManyToOne
    //@JoinColumn(name = "target_account_id")
    private Long target;

    private Date date;
    private BigDecimal amount;




}
