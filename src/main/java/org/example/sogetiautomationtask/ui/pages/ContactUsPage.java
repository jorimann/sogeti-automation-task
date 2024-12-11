package org.example.sogetiautomationtask.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.sogetiautomationtask.dto.Message;
import org.example.sogetiautomationtask.utils.MessageFactory;

public class ContactUsPage extends BasePage {

    public ContactUsPage(Page page) {
        super(page);
    }

    public ContactUsPage populateMessageData() {
        Message message = MessageFactory.getMessage();
        System.out.println(message);
        page.getByLabel("Purpose of contact *").selectOption(message.purposeOfContact());
//        page.getByPlaceholder("Enter other Purpose of contact").click();
//        page.getByPlaceholder("Enter other Purpose of contact").fill("test");
        page.getByPlaceholder("First name").fill(message.firstName());
        page.getByPlaceholder("Last name").click();
        page.getByPlaceholder("Last name").fill(message.lastName());
        page.getByPlaceholder("Job Title").click();
        page.getByPlaceholder("Job Title").fill(message.jobTitle());
        page.getByLabel("Position/Level", new Page.GetByLabelOptions().setExact(true)).selectOption("Assistant/secretary");
        page.getByPlaceholder("E-mail address").fill(message.email());
        page.getByLabel("Country *").selectOption(message.country());
        page.getByPlaceholder("Phone number").click();
        waitForElement(page.getByPlaceholder("Phone number")).fill(message.phoneNumber());
        page.getByPlaceholder("Company").fill(message.company());
        page.getByPlaceholder("Your Message").fill(message.message());
        return this;
    }

    public ContactUsPage confirmCollectingData(){
        page.getByLabel("I agree to Sogeti collecting").check();
        return this;
    }

    public ContactUsPage sendMessage(){
        Locator slider = page.locator("#slider");

        int x = (int) (slider.boundingBox().x + 10);
        int y = (int) slider.boundingBox().y;

        slider.hover();
        page.mouse().down();
        page.mouse().move(x, y);
        page.mouse().move(x + 210, y);
        page.mouse().up();
        return this;
    }

    public String getMessageAfterSending (){
        return page.getByRole(AriaRole.ALERT).innerText();
    }
}
