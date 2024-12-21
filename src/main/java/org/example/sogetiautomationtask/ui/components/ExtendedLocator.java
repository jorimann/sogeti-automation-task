package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class ExtendedLocator {
    Locator locator;
    public ExtendedLocator(Locator locator){
        this.locator = locator;
    }

    public Locator waitForElement()  {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(config().waitElement()));
        return locator;
    }
}