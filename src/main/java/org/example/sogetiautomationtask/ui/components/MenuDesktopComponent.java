package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.pages.*;
import org.example.sogetiautomationtask.ui.factory.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuDesktopComponent extends BaseComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDesktopComponent.class);

    private final Locator servicesMenuItem;
    private final Locator qualityEngineeringMenuItem;
    private final Locator automationMenuItem;

    public MenuDesktopComponent(Page page) {
        super(page);
        servicesMenuItem = page.getByLabel("Services menu", new Page.GetByLabelOptions().setExact(true));
        qualityEngineeringMenuItem = page.getByLabel("Quality Engineering menu", new Page.GetByLabelOptions().setExact(true));
        automationMenuItem = page.getByLabel("Automation menu", new Page.GetByLabelOptions().setExact(true));
    }

    @Step
    public QualityEngineeringPage goToQualityEngineeringPage() {
        hoverServiceMenuItem();
        qualityEngineeringMenuItem.click();
        PageUtils.waitForPageLoad(page);
        return PageFactory.createInstance(page, QualityEngineeringPage.class);
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
        return PageFactory.createInstance(page, AutomationPage.class);
    }

    @Step
    public ContactUsPage goToContactUs() {
        Locator contactUs = page.locator("nav.header-nav ul li.icon-investors a[href=\"/contact-us/\"]");
        contactUs.click();
        PageUtils.waitForPageLoad(page);
        return PageFactory.createInstance(page, ContactUsPage.class);
    }

    @Step
    public BasePage openGlobalMenu() {
        new ExtendedLocator(page.getByLabel("World Icon")).waitForElement().click();
        return PageFactory.createInstance(page, HomePage.class);
    }

    @Step
    public HomePage goToSubsidiarySite(String value) {
        openGlobalMenu();
        new ExtendedLocator(page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Location Search")).getByText(value))
                .waitForElement().click();
        page.waitForLoadState();
        return PageFactory.createInstance(page, HomePage.class);
    }

    @Step
    public String getClassQualityEngineeringMenu() {
        Locator locator = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Quality Engineering"));
        return locator.getAttribute("class");
    }
}
