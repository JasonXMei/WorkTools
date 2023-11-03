package com.jason.controller;

import com.jason.dto.RespDTO;
import com.jason.strategy.PaymentPlugin;
import com.jason.strategy.PaymentPluginHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentPluginHandler paymentPluginHandler;

    @GetMapping("/payment")
    public RespDTO<Void> payment(@RequestParam("paymentMethod") String paymentMethod) {
        PaymentPlugin paymentPlugin = paymentPluginHandler.getPaymentPlugin(paymentMethod);
        paymentPlugin.payment();
        return RespDTO.success(null);
    }


}
