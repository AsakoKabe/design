package com.example.provider.service;

import com.example.provider.model.Currency;
import com.example.provider.utils.Pair;
import com.example.provider.utils.RandomNumber;


public class RandomConvertor implements ConvertStrategy {
    private final float min;
    private final float max;
    private final Pair<Currency, Currency> currencyPair;

    public RandomConvertor(Pair<Currency, Currency> currencyPair, float min, float max) {
        this.min = min;
        this.max = max;
        this.currencyPair = currencyPair;

    }

    @Override
    public float getRate() {
        return RandomNumber.randomFloat(this.min, this.max);
    }


}
