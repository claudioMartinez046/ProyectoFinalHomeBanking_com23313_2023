package com.ar.bankingonline.infrastructure.repositories;

import com.ar.bankingonline.domain.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranfersRepository extends JpaRepository<Transfer, Long> {

}
