package org.example.sogetiautomationtask.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.ui.components.MenuDesktopComponent;
import org.example.sogetiautomationtask.ui.models.Message;
import org.example.sogetiautomationtask.ui.factory.MessageFactory;
import org.example.sogetiautomationtask.ui.enums.PurposeOfContact;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class ContactUsPage extends BasePage {

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
    public ContactUsPage populateMessageData() {
        Message message = MessageFactory.getMessage();

        page.getByLabel("Purpose of contact *").selectOption(message.purposeOfContact().getName());

        if (message.purposeOfContact() == PurposeOfContact.OTHER_PURPOSE) {
            page.getByPlaceholder("Enter other Purpose of contact").fill(message.otherPurposeOfContact());
        }
        page.getByPlaceholder("First name").fill(message.firstName());
        page.getByPlaceholder("Last name").fill(message.lastName());
        page.getByPlaceholder("Job Title").fill(message.jobTitle());
        page.getByLabel("Position/Level", new Page.GetByLabelOptions().setExact(true)).selectOption(message.positionLevel().getName());
        page.getByPlaceholder("E-mail address").fill(message.email());
        page.getByLabel("Country *").selectOption(message.country().getName());
        page.getByPlaceholder("Phone number").click();
        page.getByPlaceholder("Phone number").fill(message.phoneNumber());
        page.getByPlaceholder("Company").fill(message.company());
        page.getByPlaceholder("Your Message").fill(message.message());
        return this;
    }

    @Step
    public ContactUsPage confirmCollectingData() {
        Locator checkbox = page.getByLabel("I agree to Sogeti collecting");
        if (!checkbox.isChecked()) {
            checkbox.check();
        }
        return this;
    }

    @Step
    public ContactUsPage sendMessage() {
        Locator slider = page.locator("#slider");

        int x = (int) (slider.boundingBox().x + 10);
        int y = (int) slider.boundingBox().y;
        int sliderWidth = (int) slider.boundingBox().width;
        int sliderFieldWidth = (int) page.locator("#button-background").boundingBox().width;
        slider.hover();
        page.mouse().down();
        page.mouse().move(x, y);
        page.mouse().move(x + sliderFieldWidth - sliderWidth / 2, y);
        page.mouse().up();
        return this;
    }

    public String getMessageAfterSending() {
        return page.getByRole(AriaRole.ALERT).innerText();
    }
}
