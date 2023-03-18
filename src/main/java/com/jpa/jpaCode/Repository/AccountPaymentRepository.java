package com.jpa.jpaCode.Repository;

import com.jpa.jpaCode.Entity.AccountPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountPaymentRepository extends JpaRepository<AccountPayment, Long> {
    Optional<AccountPayment> findOneByToken(String token);
}
