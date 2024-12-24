package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.pages.*;
import org.example.sogetiautomationtask.ui.factory.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class MenuDesktopComponent extends BaseComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDesktopComponent.class);

    public final Locator servicesMenuItem;
    private final Locator qualityEngineeringMenuItem;
    private final Locator automationMenuItem;
    public final Locator servicesSubMenu;
    private final Locator clientStoriesMenuItem;

    public MenuDesktopComponent(Page page) {
        super(page);

        servicesMenuItem = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Services Submenu"));
        clientStoriesMenuItem = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Client stories Submenu"));
        qualityEngineeringMenuItem = page.getByLabel("Quality Engineering menu", new Page.GetByLabelOptions().setExact(true));
        automationMenuItem = page.getByLabel("Automation menu", new Page.GetByLabelOptions().setExact(true));
        servicesSubMenu = page.locator("nav[aria-label='Services SubMenu']");
    }

    @Step
    public QualityEngineeringPage goToQualityEngineeringPage() {
        hoverServiceMenuItem();
        qualityEngineeringMenuItem.click();
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
        return PageFactory.createInstance(page, AutomationPage.class);
    }

    @Step
    public ContactUsPage goToContactUs() {
        Locator contactUs = page.locator("header").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Contact us"));
        contactUs.click();
        return PageFactory.createInstance(page, ContactUsPage.class);
    }

    @Step
    public BasePage openGlobalMenu() {
        page.getByLabel("World Icon").click();
        return PageFactory.createInstance(page, HomePage.class);
    }

    @Step
    public SubsidiaryPage goToSubsidiarySite(String value) {
        openGlobalMenu();
        page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Location Search"))
                .getByText(Pattern.compile(value))
                .click();
        return PageFactory.createInstance(page, SubsidiaryPage.class);
    }

    @Step
    public String getClassQualityEngineeringMenu() {
        Locator locator = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Quality Engineering"));
        return locator.getAttribute("class");
    }

    public Boolean isServicesSubMenuVisible() {
        return servicesSubMenu.isVisible();
    }
}
