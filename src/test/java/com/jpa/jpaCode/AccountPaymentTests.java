package com.jpa.jpaCode;

import com.jpa.jpaCode.Repository.AccountPaymentRepository;
import com.jpa.jpaCode.Service.ServiceImpl.AccountPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountPaymentTests {

    @Autowired
    private AccountPaymentService accountPaymentService;

    @Autowired
    private AccountPaymentRepository accountPaymentRepository;

    @Test
    void shouldIncrementItemAmount_withoutConcurrency() {
        var amount = 10;
        var accountPaymentOpt = accountPaymentRepository.findOneByToken("fc5243ce-cadf-4730-a9c8-8933428bfa8a");
        if (accountPaymentOpt.isPresent()) {
            var accountPayment = accountPaymentOpt.get();
            var currentBalance = accountPayment.getBalance();
            accountPayment.setBalance(currentBalance + currentBalance);
            accountPaymentRepository.save(accountPayment);
            accountPayment.setBalance(currentBalance + currentBalance + 1);
            accountPaymentRepository.save(accountPayment);

        }
    }



}
