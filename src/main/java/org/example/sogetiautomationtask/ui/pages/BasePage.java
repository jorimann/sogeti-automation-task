package org.example.sogetiautomationtask.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.example.sogetiautomationtask.components.MenuDesktopComponent;
import org.example.sogetiautomationtask.components.SubNavigationComponent;

public class BasePage {

    protected Page page;
    protected Locator bannerTag;
    protected final MenuDesktopComponent menu;
    protected final SubNavigationComponent subNavigation;

    public BasePage(Page page){
        this.page = page;
        this.menu = new MenuDesktopComponent(page);
        this.subNavigation = new SubNavigationComponent(page);
        bannerTag = page.locator(".banner-tag");
    }

    public String getTextFromBannerTag(){
        return bannerTag.innerText();
    }

    public String getUrl(){
//        page.waitForLoadState();
//        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return page.url();
    }
    public MenuDesktopComponent getMenu(){
        return menu;
    }

    public SubNavigationComponent getSubNavigation(){
        return subNavigation;
    }

    public String getTitle(){
        return page.title();
    }

    protected Locator waitForElement(Locator locator){
        // Время начала ожидания
        long startTime = System.currentTimeMillis();
        // Максимальное время ожидания в миллисекундах
        int timeout = 5000; // 5 секунд
        int pollInterval = 200; // Интервал между проверками (200 мс)

        // Цикл ожидания
        while (System.currentTimeMillis() - startTime < timeout) {
            if (locator.isVisible() && locator.isEnabled()) {
                System.out.println("Элемент доступен для клика");
                return locator;
            } else {
                System.out.println("элемент пока недоступен");
            }
            // Ждём перед следующей проверкой
            try {
                Thread.sleep(pollInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return locator;
    }
}
