package com.jpa.jpaCode.abstracts;

public class CreditCardAccountManagement extends AccountManagement {

    @Override
    public void withdrawAccount() {
        System.out.println(luu);
        accountPaymentService.debitAccount("",2);
    }

    @Override
    public void fundAccount() {

    }
}
