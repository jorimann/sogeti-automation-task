package org.example.sogetiautomationtask.ui.utils;

public enum PurposeOfContact {
    PRIVACY("Privacy"),
    ALUMNI("Alumni"),
    BUSINESS("Business"),
    CAREER("Career"),
    CUSTOMER_SUPPORT("Customer Support"),
    OTHER_PURPOSE("Other Purpose");

    private final String name;

    PurposeOfContact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
