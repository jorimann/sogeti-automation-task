package org.example.sogetiautomationtask.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import static org.example.sogetiautomationtask.utils.ConfigurationManager.config;

public class BrowserManager {
    private BrowserManager() {};

    public static Browser getBrowser(final Playwright playwright){
        return BrowserFactory.valueOf(config().browser().toUpperCase()).createInstance(playwright);
    }


}
