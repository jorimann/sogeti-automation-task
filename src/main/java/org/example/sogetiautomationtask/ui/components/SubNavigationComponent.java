package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Page;

public class SubNavigationComponent extends BaseComponent {

    public SubNavigationComponent(Page page) {
        super(page);
    }

    public String getSubNavigationText() {
        return page.locator(".sub-navigation ").innerText();
    }
}
