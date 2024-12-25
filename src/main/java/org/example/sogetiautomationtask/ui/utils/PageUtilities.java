package org.example.sogetiautomationtask.ui.utils;

import java.util.function.BooleanSupplier;

public class PageUtilities {
    public static boolean waitForCondition(BooleanSupplier supplier, int timeWaitMs, int timeIntervalMs) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + timeWaitMs) {
            if (supplier.getAsBoolean()) {
                return true;
            }
            try {
                Thread.sleep(timeIntervalMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return supplier.getAsBoolean();
    }
}
