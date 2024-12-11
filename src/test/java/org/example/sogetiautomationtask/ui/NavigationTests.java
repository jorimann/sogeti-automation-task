package org.example.sogetiautomationtask.ui;

import org.example.sogetiautomationtask.ui.pages.AutomationPage;
import org.example.sogetiautomationtask.ui.pages.ContactUsPage;
import org.example.sogetiautomationtask.ui.pages.QualityEngineeringPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NavigationTests extends BaseTest {
    private static final String CSV_PATH = "/sites.csv";
    private static final String EXPECTED_MESSAGE_AFTER_SUBMISSION = "Thank you! We have received your information successfully and will review it shortly.";
    private static final String EXPECTED_AUTOMATION_BANNER_TEXT = "Automation";
    private static final String EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT = "Quality Engineering";

    @Test
    @DisplayName("Verify Automation link should be available as submenu item of Service header menu")
    void tc1ShouldAutomationLinkAvailableFromServiceHeaderMenu() {
        AutomationPage automationPage = homePage.getMenu().goToAutomationMenuItem();

        assertEquals(EXPECTED_AUTOMATION_BANNER_TEXT, automationPage.getTextFromBannerTag(),
                "Automation text is not present on Banner");

        assertTrue(automationPage.getSubNavigation().getSubNavigationText().contains(EXPECTED_AUTOMATION_BANNER_TEXT),
                "Subnavigation does not contain Automation item");
    }

    @Test
    @DisplayName("Verify Quality Engineering link should be available as submenu item of Service header menu")
    void tc1bShouldQualityEngineeringLinkAvailableFromServiceHeaderMenu() {
        //my assumption that selected menu item has active attribute in class name
        String SUPPOSED_ACTIVE_ATTRIBUTE = "active";

        QualityEngineeringPage qualityEngineeringPage = homePage.getMenu().goToQualityEngineeringPage();

        assertEquals(EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT, qualityEngineeringPage.getTextFromBannerTag(),
                "Automation text is not present on Banner");
        assertTrue(qualityEngineeringPage.getSubNavigation().getSubNavigationText().contains(EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT),
                "Subnavigation does not contain Quality Engineering item");

        qualityEngineeringPage.getMenu().hoverServiceMenuItem();

        assertTrue(qualityEngineeringPage.getMenu().getClassServiceMenu().contains(SUPPOSED_ACTIVE_ATTRIBUTE),
                "Service menu item is not active");
        assertTrue(qualityEngineeringPage.getMenu().getClassQualityEngineeringMenu().contains(SUPPOSED_ACTIVE_ATTRIBUTE),
                "Quality Engineering Menu Item is not active");
    }

    @Test
    @DisplayName("Verify User can send Message from Automation Page")
    void tc2ShouldSendMessageViaContactUsFormFromAutomationPage() {
        homePage.getMenu().goToAutomationMenuItem();
    }

    @Test
    @DisplayName("Verify User can send Message from Contact Us page")
    void tc2bShouldSendMessageViaContactUsFormFromAutomationPage() {
        ContactUsPage contactUsPage = homePage.getMenu().goToContactUs();
        contactUsPage.populateMessageData();
//TODO:        contactUsPage.confirmCollectingData();
        contactUsPage.sendMessage();
        assertTrue(contactUsPage.getMessageAfterSending().contains(EXPECTED_MESSAGE_AFTER_SUBMISSION),
                "Page does not contain expected text after sending message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = CSV_PATH, numLinesToSkip = 1)
    @DisplayName("Verify User can navigate to Country-specific site")
    void tc3ShouldCountrySpecificSitesAvailable(String country, String url) {
        homePage.getMenu().goToSubsidiarySite(country);
        homePage.waitForUrl(url);
        assertEquals(url, homePage.getUrl(), "link leads to unexpected site");
    }
}
