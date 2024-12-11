package org.example.sogetiautomationtask.ui.utils;

public enum PositionLevel {
    ASSISTANT_SECRETARY("Assistant/secretary"),
    CDO_CMO("CDO/CMO"),
    PURCHASER("Purchaser"),
    CEO("CEO"),
    CFO("CFO"),
    VICE_PRESIDENT("Vice President");

    private final String name;

    PositionLevel(String name) {
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
