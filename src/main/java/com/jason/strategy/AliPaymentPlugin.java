package com.jason.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("alipay")
public class AliPaymentPlugin implements PaymentPlugin {

    @Override
    public void payment() {
        log.info("ali payment");
    }

}
