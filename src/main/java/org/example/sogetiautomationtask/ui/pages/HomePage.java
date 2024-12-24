package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.PlaywrightException;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class HomePage extends BasePage {
    private MenuDesktopComponent menu;

    @Override
    public void initComponents(){
        menu = new MenuDesktopComponent(page);
    }

    @Override
    public void waitForPageLoad() {
        super.waitForPageLoad();
        waitPageOperational();
    }

    private void waitPageOperational(){
        int i = 0;
        while (i++ < config().timeout() / 200) {
            menu.hoverServiceMenuItem();

            int j = 0;
            while (!menu.isServicesSubMenuVisible() && j < 10) {
                try {
                    Thread.sleep(50);
                    j++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (menu.isServicesSubMenuVisible()){
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
