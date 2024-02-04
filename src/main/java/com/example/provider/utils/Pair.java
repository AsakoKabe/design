package com.example.provider.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data(staticConstructor = "of")
@AllArgsConstructor
public class Pair<A, B> {
    private final A left;
    private final B right;
}