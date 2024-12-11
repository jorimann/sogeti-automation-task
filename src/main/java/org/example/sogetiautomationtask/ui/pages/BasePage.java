package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Page;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;
import org.example.sogetiautomationtask.ui.components.SubNavigationComponent;
import org.example.sogetiautomationtask.ui.utils.ConfigReader;

public class BasePage {

    private final MenuDesktopComponent menu;
    private final SubNavigationComponent subNavigation;
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
        this.menu = new MenuDesktopComponent(page);
        this.subNavigation = new SubNavigationComponent(page);
    }

    public String getTextFromBannerTag() {
        return page.locator(".banner-tag").innerText();
    }

    public String getUrl() {
        return page.url();
    }

    public MenuDesktopComponent getMenu() {
        return menu;
    }

    public SubNavigationComponent getSubNavigation() {
        return subNavigation;
    }

    public String getTitle() {
        return page.title();
    }

    public void waitForUrl(String expectedUrl) {
        page.waitForURL(url -> url.equals(expectedUrl), new Page.WaitForURLOptions().setTimeout(ConfigReader.getInt("ui.timeout.page.load")));
    }
}
