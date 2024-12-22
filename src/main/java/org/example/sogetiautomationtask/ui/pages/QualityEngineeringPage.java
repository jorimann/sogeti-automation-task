package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;
import org.example.sogetiautomationtask.ui.components.SubNavigationComponent;

public class QualityEngineeringPage extends BasePage{
    private MenuDesktopComponent menu;
    private SubNavigationComponent subNavigation;

    @Override
    public void initComponents(){
        menu = new MenuDesktopComponent(page);
        subNavigation = new SubNavigationComponent(page);
    }

    @Step
    public String getSubNavigationText() {
        return subNavigation.getSubNavigationText();
    }

    @Step
    public void hoverServiceMenuItem(){
        menu.hoverServiceMenuItem();
    }

    @Step
    public String getClassServiceMenu(){
        return menu.getClassServiceMenu();
    }

    @Step
    public String getClassQualityEngineeringMenu(){
        return menu.getClassQualityEngineeringMenu();
    }
}
