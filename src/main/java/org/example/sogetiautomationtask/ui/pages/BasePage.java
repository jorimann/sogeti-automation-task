package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.utils.PageUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public abstract class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    protected Page page;
    protected Locator logo;
    protected Locator servicesSubMenu;
    protected Locator servicesMenuItem;

    public void setAndConfigurePage(final Page page){
        this.page = page;
        this.logo = page.locator(".logo").first();
        this.servicesSubMenu = page.locator("nav[aria-label='Services SubMenu']");
        this.servicesMenuItem = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Services Submenu"));

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

    protected void waitPageOperational() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + config().timeout()) {
            servicesMenuItem.hover();
            if (PageUtilities.waitForCondition(()->servicesSubMenu.isVisible(),2000, 100)){
                logo.hover();
                return;
            } else {
                logo.hover();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new PlaywrightException("Services submenu is not visible after hovering Services menu item");
    }
}
