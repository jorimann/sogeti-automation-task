package org.example.sogetiautomationtask.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import org.example.sogetiautomationtask.pages.*;

import java.util.regex.Pattern;

public class MenuDesktopComponent {
    private Page page;

    private Locator services;
    private Locator qualityEngineering;
    private Locator automation;

    public MenuDesktopComponent(Page page){
        this.page = page;
        services = page.getByLabel("Services menu");
        qualityEngineering = page.getByLabel("Quality Engineering menu");
        automation = page.getByLabel("Automation menu");
    }

    public QualityEngineeringPage invokeQualityEngineeringMenuItem(){
        services.hover();
        qualityEngineering.click();
        return new QualityEngineeringPage(page);
    }

    public void hoverServiceElement(){
        // Максимальное время ожидания в миллисекундах
        int timeout = 5000; // 5 секунд
        int pollInterval = 200; // Интервал между проверками (200 мс)

        // Время начала ожидания
        long startTime = System.currentTimeMillis();

        // Цикл ожидания
        while (System.currentTimeMillis() - startTime < timeout) {
            if (services.isVisible() && services.isEnabled()) {
                System.out.println("Элемент доступен для клика");
                break; // Прерываем цикл после успешного клика
            }

            // Ждём перед следующей проверкой
            try {
                Thread.sleep(pollInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        services.hover();
    }

    public String getClassServiceMenu(){




        Locator serviceMenu = page.getByLabel("Services menu");
//        Locator parentServiceMenu = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHas(serviceMenu));
        Locator parentServiceMenu = page.locator("#menu-main-desktop > li:nth-child(1)");
//        parentServiceMenu.hover(new Locator.HoverOptions().setTimeout(1000).setForce(true).setTrial(true));


        parentServiceMenu.hover();
        System.out.println(parentServiceMenu.getAttribute("class"));


        // Ждем, чтобы проверить логи

        System.out.println(parentServiceMenu.getAttribute("class"));
        System.out.println(parentServiceMenu.getAttribute("aria-label"));
        return parentServiceMenu.getAttribute("class");
    }

    public AutomationPage invokeAutomationMenuItem(){
        services.hover();
        automation.click();
        return new AutomationPage(page);
    }

    public ContactUsPage navigateContuctUs(){
//        Locator contactUs = page.getByLabel("Contact us");
        Locator contactUs = waitForElement(page.locator("nav.header-nav ul li.icon-investors a[href=\"/contact-us/\"]"));
        contactUs.click();
        return new ContactUsPage(page);
    }
    public BasePage openGlobalMenu(){
//        Locator global = waitForElement(page.getByText(Pattern.compile("Global | EN")));
        Locator global = waitForElement(page.getByLabel("World Icon"));
        global.click();
        return new HomePage(page);
    }

    public BasePage navigateToSubsidiary(String value, String address){
        openGlobalMenu();
        Locator menu = page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Location Search"));
        Locator country = menu.getByText(value);

        country.click();
//        page.waitForLoadState();
//        page.route("**", route -> route.abort());
//        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(10000));
        System.out.println(page.url());
        page.waitForLoadState();
        System.out.println(page.url());
        page.waitForURL(address); // Ждём, пока URL изменится
        System.out.println("Current URL: " + page.url());
        return new BasePage(page);
    }

    private Locator waitForElement(Locator locator){
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
