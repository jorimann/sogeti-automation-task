package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocatorUtility {
    private static final Logger logger = LoggerFactory.getLogger(LocatorUtility.class);
    public static Locator waitForElement(Locator locator){
        long startTime = System.currentTimeMillis();
        int timeout = 5000;
        int pollInterval = 200;

        while (System.currentTimeMillis() - startTime < timeout) {
            if (locator.isVisible() && locator.isEnabled()) {
                return locator;
            }
            try {
                Thread.sleep(pollInterval);
            } catch (InterruptedException e) {
                logger.error("element after await is still inactive. element: " + locator, e.getMessage());
            }
        }
        return locator;
    }
}
