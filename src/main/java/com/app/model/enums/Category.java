package com.app.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    CHILDREN("0-3"),
    TEENAGER("4-7"),
    ADULT("8-12"),
    ADULT3("13-16"),
    ADULT4("16-18"),
    ADULT5("18+"),
    ;
    private final String name;
}

