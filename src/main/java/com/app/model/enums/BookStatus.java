package com.app.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookStatus {
    ACTIVE("Свободна"),
    BOOKED("Забронирована"),
    TAKEN("Взята"),
    ;
    private final String name;
}

