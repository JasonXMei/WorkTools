package com.jason.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentPluginHandler {

    @Autowired
    private Map<String, PaymentPlugin> paymentPluginMap = new HashMap<>();

    public PaymentPlugin getPaymentPlugin(String paymentMethod) {
        return paymentPluginMap.get(paymentMethod);
    }

}
