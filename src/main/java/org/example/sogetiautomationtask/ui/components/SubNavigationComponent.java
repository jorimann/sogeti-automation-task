package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Page;

public class SubNavigationComponent {

    private final Page page;

    public SubNavigationComponent(Page page) {
        this.page = page;
    }

    public String getSubNavigationText() {
        return page.locator(".sub-navigation ").innerText();
    }
}
