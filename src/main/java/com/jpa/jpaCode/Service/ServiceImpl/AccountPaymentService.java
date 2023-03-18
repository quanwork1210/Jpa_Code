package com.jpa.jpaCode.Service.ServiceImpl;

public interface AccountPaymentService {

    void createAccount(long userID);

    void creditAccount(String token, double balance);

    void debitAccount(String token, double balance);
}
