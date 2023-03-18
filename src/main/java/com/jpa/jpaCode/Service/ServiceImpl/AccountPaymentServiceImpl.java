package com.jpa.jpaCode.Service.ServiceImpl;

import com.jpa.jpaCode.Entity.AccountPayment;
import com.jpa.jpaCode.Repository.AccountPaymentRepository;
import com.jpa.jpaCode.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccountPaymentServiceImpl implements AccountPaymentService {

    @Autowired
    private AccountPaymentRepository accountPaymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void debitAccount(String token, double balance) {
        var accountPaymentOpt = accountPaymentRepository.findOneByToken(token);
        if (accountPaymentOpt.isEmpty()) {
            return;
        }
        var accountPayment = accountPaymentOpt.get();
        var currentBalance = accountPayment.getBalance();
        accountPayment.setBalance(balance + currentBalance);
        accountPaymentRepository.save(accountPayment);
    }

    @Override
    public void creditAccount(String token, double amount) {
        var accountPaymentOpt = accountPaymentRepository.findOneByToken(token);
        if (accountPaymentOpt.isEmpty()) {
            return;
        }
        var accountPayment = accountPaymentOpt.get();
        var currentBalance = accountPayment.getBalance();
        if (currentBalance < amount) {
            log.warn("Error balance");
            return;
        }
        accountPayment.setBalance(currentBalance - amount);
        accountPaymentRepository.save(accountPayment);
    }

    @Override
    public void createAccount(long userID) {
        var userOpt = userRepository.findById(userID);
        if (userOpt.isEmpty()) {
            return;
        }
        var account = AccountPayment.builder().balance(0.0).token(UUID.randomUUID().toString()).user(userOpt.get()).build();
        accountPaymentRepository.save(account);
    }
}
