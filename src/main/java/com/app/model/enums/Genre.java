package com.app.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Genre {
    FANTASY("Фантастика"),
    HORROR("Ужасы"),
    DETECTIVE("Детектив"),
    ROMANCE("Романы"),
    PSYCHOLOGY("Психология"),
    CLASSIC("Классика"),
    ;
    private final String name;
}

