package com.stone.holidayapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HolidayType {

    PUBLIC ("Public"),
    BANK ("Bank"),
    SCHOOL ("School"),
    AUTHORITIES ("Authorities"),
    OPTIONAL ("Optional"),
    OBSERVANCE ("Observance");

    final String value;

    HolidayType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static HolidayType fromString(String value) {
        return value == null
                ? null
                : HolidayType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
