package org.example.sogetiautomationtask.ui.enums;

public enum Country {
    ALBANIA("Albania"),
    BELGIUM("Belgium"),
    GERMANY("Germany");
    private final String name;

    Country(String name) {
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
