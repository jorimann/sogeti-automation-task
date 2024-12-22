package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public abstract class BasePage {
    protected Page page;

    public void setAndConfigurePage(final Page page){
        this.page = page;
    }

    public void initComponents(){
    }

    @Step
    public String getTextFromBannerTag() {
        return page.locator(".banner-tag").innerText();
    }

    public String getUrl() {
        return page.url();
    }

    public String getTitle() {
        return page.title();
    }

    @Step
    public void waitForUrl(String expectedUrl) {
        page.waitForURL(url -> url.equals(expectedUrl), new Page.WaitForURLOptions().setTimeout(config().waitForNewPage()));
    }

    public Page getPage(){
        return this.page;
    }
}
