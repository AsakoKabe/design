package com.example.provider.utils;

import java.util.Random;

public class RandomNumber {
    public static float randomFloat(float min, float max){
        Random r = new Random();
        return min + r.nextFloat() * (max - min);
    }
}
