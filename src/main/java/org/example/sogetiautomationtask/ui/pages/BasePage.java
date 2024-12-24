package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public abstract class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    protected Page page;
    protected Locator logo;

    public void setAndConfigurePage(final Page page){
        this.page = page;
        this.logo = page.locator(".logo").first();
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

    public void removeFocusFromElement() {
        logo.click();
    }

    public Page getPage(){
        return this.page;
    }

    public void waitForPageLoad() {
        try {
            page.waitForLoadState(LoadState.LOAD,
                    new Page.WaitForLoadStateOptions()
                            .setTimeout(config().timeoutPageLoad()));
        } catch (TimeoutError e) {
            LOGGER.warn("page load was not completed in defined timeout, but script attempts to run further");
        }
    }
}
