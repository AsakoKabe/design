package com.example.provider.controller;

import com.example.provider.model.Currency;
import com.example.provider.model.transfer.Transfer;
import com.example.provider.model.transfer.TransferService;
import com.example.provider.service.RandomConvertor;
import com.example.provider.service.CurrencyConvertor;
import com.example.provider.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RateController {
    private final CurrencyConvertor currencyConvertor;
    private static final int pushRate = 60000; // 60000 ms == 1 min
    private final TransferService transferService;

    @Autowired
    public RateController(CurrencyConvertor currencyConvertor, TransferService transferService) {
        this.transferService = transferService;
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
    @Scheduled(fixedRate = pushRate)
    public void writeToDatabase() {
        Pair<Currency, Currency> currencyPair = new Pair<>(Currency.USD, Currency.RUB);

        Transfer transfer = this.currencyConvertor.createTransfer(currencyPair);
        transferService.saveTransfer(transfer);
    }

}