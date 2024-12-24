package org.example.sogetiautomationtask.ui.factory;

import com.microsoft.playwright.Page;
import org.example.sogetiautomationtask.ui.pages.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFactory {
    static Logger LOGGER = LoggerFactory.getLogger(PageFactory.class);
    private PageFactory(){};

    public static <T extends BasePage> T createInstance(final Page page, final Class<T> clazz){
        try{
            BasePage instance = clazz.getDeclaredConstructor().newInstance();

            instance.setAndConfigurePage(page);
            instance.initComponents();
            instance.waitForPageLoad();
            return clazz.cast(instance);
        } catch (Exception e) {
            LOGGER.error("Page class instantiation failed.", e);
        }
        throw new NullPointerException("Page class instantiation failed.");
    }
}
