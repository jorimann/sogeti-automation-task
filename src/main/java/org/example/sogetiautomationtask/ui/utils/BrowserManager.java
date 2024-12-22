package org.example.sogetiautomationtask.ui.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.example.sogetiautomationtask.ui.factory.BrowserFactory;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class BrowserManager {
    private BrowserManager() {}

    public static Browser getBrowser(final Playwright playwright){
        return BrowserFactory.valueOf(config().browserType().toUpperCase()).createInstance(playwright);
    }
}
