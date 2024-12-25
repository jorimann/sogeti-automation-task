package org.example.sogetiautomationtask.ui.pages;

import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;

public class HomePage extends BasePage {
    private MenuDesktopComponent menu;

    @Override
    public void initComponents(){
        menu = new MenuDesktopComponent(page);
    }

    @Override
    public void waitForPageLoad() {
        super.waitForPageLoad();
        super.waitPageOperational();
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
    public SubsidiaryPage goToSubsidiarySite(String site){
        return menu.goToSubsidiarySite(site);
    }
}
