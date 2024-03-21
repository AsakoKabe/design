package com.example.provider.service;


import com.example.provider.model.Currency;
import com.example.provider.model.Transfer;
import com.example.provider.utils.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        if (isConversionExist(strategy)) {
            return null;
        }
        return new Transfer(currencyPair.getLeft(), currencyPair.getRight(), strategy.getRate());
    }

    private static boolean isConversionExist(ConvertStrategy strategy) {
        return strategy == null;
    }
}
