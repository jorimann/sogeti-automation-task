package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;
import org.example.sogetiautomationtask.ui.components.SubNavigationComponent;

public class HomePage extends BasePage {
    private MenuDesktopComponent menu;
    private SubNavigationComponent subNavigation;

    @Override
    public void initComponents(){
        menu = new MenuDesktopComponent(page);
        subNavigation = new SubNavigationComponent(page);
    }

    @Step
    public AutomationPage goToAutomationMenuItem() {
        return menu.goToAutomationMenuItem();
    }

    @Step
    public QualityEngineeringPage goToQualityEngineeringPage(){
        return menu.goToQualityEngineeringPage();
    }

    @Step
    public ContactUsPage goToContactUs(){
        return menu.goToContactUs();
    }

    @Step
    public HomePage goToSubsidiarySite(String site){
        return menu.goToSubsidiarySite(site);
    }
}
