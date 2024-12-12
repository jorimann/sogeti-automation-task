package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.pages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuDesktopComponent {
    private static final Logger logger = LoggerFactory.getLogger(MenuDesktopComponent.class);

    private final Page page;

    private final Locator servicesMenuItem;
    private final Locator qualityEngineeringMenuItem;
    private final Locator automationMenuItem;

    public MenuDesktopComponent(Page page) {
        this.page = page;
        servicesMenuItem = page.getByLabel("Services menu", new Page.GetByLabelOptions().setExact(true));
        qualityEngineeringMenuItem = page.getByLabel("Quality Engineering menu", new Page.GetByLabelOptions().setExact(true));
        automationMenuItem = page.getByLabel("Automation menu", new Page.GetByLabelOptions().setExact(true));
    }

    @Step
    public QualityEngineeringPage goToQualityEngineeringPage() {
        hoverServiceMenuItem();
        qualityEngineeringMenuItem.click();
        PageUtils.waitForPageLoad(page);
        return new QualityEngineeringPage(page);
    }

    @Step
    public void hoverServiceMenuItem() {
        servicesMenuItem.hover();
    }

    public String getClassServiceMenu() {
        Locator serviceMenu = page.getByLabel("Services menu");
        Locator parentServiceMenu = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHas(serviceMenu));
        return parentServiceMenu.getAttribute("class");
    }

    @Step
    public AutomationPage goToAutomationMenuItem() {
        hoverServiceMenuItem();
        automationMenuItem.click();
        PageUtils.waitForPageLoad(page);
        return new AutomationPage(page);
    }

    @Step
    public ContactUsPage goToContactUs() {
        Locator contactUs = page.locator("nav.header-nav ul li.icon-investors a[href=\"/contact-us/\"]");
        contactUs.click();
        PageUtils.waitForPageLoad(page);
        return new ContactUsPage(page);
    }

    @Step
    public BasePage openGlobalMenu() {
        new ExtendedLocator(page.getByLabel("World Icon")).waitForElement().click();
        return new HomePage(page);
    }

    @Step
    public HomePage goToSubsidiarySite(String value) {
        openGlobalMenu();
        new ExtendedLocator(page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Location Search")).getByText(value))
                .waitForElement().click();
        page.waitForLoadState();
        return new HomePage(page);
    }

    @Step
    public String getClassQualityEngineeringMenu() {
        Locator locator = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Quality Engineering"));
        return locator.getAttribute("class");
    }
}
