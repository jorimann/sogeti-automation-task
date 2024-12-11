package org.example.sogetiautomationtask.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SubNavigationComponent {

    private Page page;

    private Locator subNavigation;

    public SubNavigationComponent(Page page){
        this.page = page;
        subNavigation = page.locator(".sub-navigation ");
    }

    public String getSubNavigationText(){
        return subNavigation.innerText();
    }

}
