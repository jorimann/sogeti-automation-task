package org.example.sogetiautomationtask.ui.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    private BrowserManager() {}

    public static Browser getBrowser(final Playwright playwright){
        return BrowserFactory.valueOf(ConfigReader.get("ui.browsertype").toUpperCase()).createInstance(playwright);
    }
}
