package com.shankarstudy.microservices.currency_conversion_service.proxy;

import com.shankarstudy.microservices.currency_conversion_service.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "currency-exchange", url = "localhost:8000")
// Make it load balancing
@FeignClient(name = "CURRENCY-EXCHANGE")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,
                                                    @PathVariable("to") String to);
}
