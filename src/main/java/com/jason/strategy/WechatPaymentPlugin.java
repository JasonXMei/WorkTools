package com.jason.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("wechatpay")
public class WechatPaymentPlugin implements PaymentPlugin {

    @Override
    public void payment() {
        log.info("wechat payment");
    }

}
