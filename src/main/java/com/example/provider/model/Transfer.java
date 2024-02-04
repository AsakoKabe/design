package com.example.provider.model;

import lombok.Data;

@Data
public class Transfer {
    private final Currency from;
    private final Currency to;
    private final float value;
}
