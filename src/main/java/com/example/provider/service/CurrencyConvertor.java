package com.example.provider.service;


import com.example.provider.model.Currency;
import com.example.provider.model.transfer.Transfer;
import com.example.provider.utils.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CurrencyConvertor {
    private final Map<Pair<Currency, Currency>, ConvertStrategy> convertMapping;

    public CurrencyConvertor() {
        this.convertMapping = new HashMap<>();
    }

    public void addConvertMapping(Pair<Currency, Currency> currencyPair, ConvertStrategy strategy){
        this.convertMapping.put(currencyPair, strategy);
    }

    public Transfer createTransfer(Pair<Currency, Currency> currencyPair){
        ConvertStrategy strategy = this.convertMapping.get(currencyPair);
        if (isValidTransfer(currencyPair, strategy)) {
            return this.createZeroTransfer(currencyPair);
        }
        Transfer transfer =  new Transfer();
        transfer.setFrom(currencyPair.getLeft());
        transfer.setTo(currencyPair.getRight());
        transfer.setValue(strategy.getRate());

        return transfer;
    }

    private boolean isValidTransfer(Pair<Currency, Currency> currencyPair, ConvertStrategy strategy) {
        return isConversionExist(strategy) || this.isSameCurrency(currencyPair);
    }

    private Transfer createZeroTransfer(Pair<Currency, Currency> currencyPair) {
        Transfer transfer =  new Transfer();
        transfer.setFrom(currencyPair.getLeft());
        transfer.setTo(currencyPair.getRight());
        return transfer;
    }

    private boolean isSameCurrency(Pair<Currency, Currency> currencyPair) {
        return currencyPair.getLeft().equals(currencyPair.getRight());
    }

    private static boolean isConversionExist(ConvertStrategy strategy) {
        return strategy == null;
    }
}
