package com.jpa.jpaCode.Controller;

import com.jpa.jpaCode.Service.ServiceImpl.AccountPaymentService;
import com.jpa.jpaCode.model.GeneralApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private AccountPaymentService accountPaymentService;

    @PostMapping("/{userID}")
    public GeneralApiResponse createAccountPayment(@PathVariable long userID) {
        accountPaymentService.createAccount(userID);
        return new GeneralApiResponse<>(1, "SUCCESS");
    }

    @PostMapping("/credit")
    public GeneralApiResponse creditPayment(
            @RequestParam("token") String token,
            @RequestParam("amount") double amount) {
        accountPaymentService.creditAccount(token, amount);
        return new GeneralApiResponse(1, "SUCCESS");
    }

    @PostMapping("/debit")
    public GeneralApiResponse debitPayment(@RequestParam("token") String token,
                                           @RequestParam("amount") double amount) {
        accountPaymentService.debitAccount(token, amount);
        return new GeneralApiResponse(1, "SUCCESS");
    }

}
