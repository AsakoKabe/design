package com.example.provider.controller;

import com.example.provider.model.Currency;
import com.example.provider.model.Transfer;
import com.example.provider.service.RandomConvertor;
import com.example.provider.service.CurrencyConvertor;
import com.example.provider.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {
    private final CurrencyConvertor currencyConvertor;
    private int count = 0;

    @Autowired
    public RateController(CurrencyConvertor currencyConvertor) {
        this.currencyConvertor = currencyConvertor;
        this.addCurrencyConvertorMapping();
    }

    private void addCurrencyConvertorMapping() {
        Pair<Currency, Currency> usd2rub = new Pair<>(Currency.USD, Currency.RUB);
        this.currencyConvertor.addConvertMapping(
                usd2rub,
                new RandomConvertor(usd2rub, 0, 100)
        );

        Pair<Currency, Currency> rub2usd = new Pair<>(Currency.RUB, Currency.USD);
        this.currencyConvertor.addConvertMapping(
                rub2usd,
                new RandomConvertor(rub2usd, -100, 0)
        );

    }

    @GetMapping(value = "/rate/{from}/{to}")
    public ResponseEntity<?> getRate(
            @PathVariable(name = "from") Currency from,
            @PathVariable(name = "to") Currency to
    ) {
        count += 1;
        System.out.println(count);
        Transfer transfer = createTransfer(from, to);

        return transfer != null
                ? new ResponseEntity<>(transfer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Transfer createTransfer(Currency from, Currency to) {
        Pair<Currency, Currency> currencyPair = new Pair<>(from, to);
        if (isSameCurrency(currencyPair)){
            return createZeroTransfer(currencyPair);
        }

        return this.currencyConvertor.createTransfer(currencyPair);
    }

    private static Transfer createZeroTransfer(Pair<Currency, Currency> currencyPair) {
        return new Transfer(currencyPair.getLeft(), currencyPair.getRight(), 0);
    }

    private static boolean isSameCurrency(Pair<Currency, Currency> currencyPair) {
        return currencyPair.getLeft().equals(currencyPair.getRight());
    }

}