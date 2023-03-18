package com.jpa.jpaCode.abstracts;

import com.jpa.jpaCode.Service.ServiceImpl.AccountPaymentService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccountManagement {

    protected String luu = "";

    @Autowired
    protected AccountPaymentService accountPaymentService;

    public abstract void withdrawAccount();

    public abstract void fundAccount();

    public String test() {
        return "";
    }

}
