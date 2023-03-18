package com.jpa.jpaCode.abstracts;

import org.springframework.stereotype.Service;

@Service
public class AccountFacade {

    public void withdrawAccount() {
        AccountManagement creditCard = new CreditCardAccountManagement();
        creditCard.test();


    }

}
