package org.example.sogetiautomationtask.ui;

import com.microsoft.playwright.assertions.PageAssertions;
import org.example.sogetiautomationtask.ui.pages.AutomationPage;
import org.example.sogetiautomationtask.ui.pages.ContactUsPage;
import org.example.sogetiautomationtask.ui.pages.QualityEngineeringPage;
import org.example.sogetiautomationtask.ui.pages.SubsidiaryPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.sogetiautomationtask.config.ConfigurationManager.config;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NavigationTests extends BaseTest {
    private static final String CSV_PATH = "/sites.csv";
    private static final String EXPECTED_MESSAGE_AFTER_SUBMISSION = "Thank you! We have received your information successfully and will review it shortly.";
    private static final String EXPECTED_AUTOMATION_BANNER_TEXT = "Automation";
    private static final String EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT = "Quality Engineering";

    @Test
    @DisplayName("[Expected to fail] Verify Automation link should be available as submenu item of Service header menu")
    void tc1ShouldAutomationLinkAvailableFromServiceHeaderMenu() {
        AutomationPage automationPage = homePage.goToAutomationMenuItem();

        assertEquals(EXPECTED_AUTOMATION_BANNER_TEXT, automationPage.getTextFromBannerTag(),
                "Automation text is not present on Banner");

        assertTrue(automationPage.getSubNavigationText().contains(EXPECTED_AUTOMATION_BANNER_TEXT),
                "Sub-navigation does not contain Automation item");
    }

    @Test
    @DisplayName("[Expected to fail] Verify Quality Engineering link should be available as submenu item of Service header menu")
    void tc1bShouldQualityEngineeringLinkAvailableFromServiceHeaderMenu() {
        //my assumption that selected menu item has active attribute in class name
        String SUPPOSED_ACTIVE_ATTRIBUTE = "active";

        QualityEngineeringPage qualityEngineeringPage = homePage.goToQualityEngineeringPage();

        assertEquals(EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT, qualityEngineeringPage.getTextFromBannerTag(),
                "Automation text is not present on Banner");
        assertTrue(qualityEngineeringPage.getSubNavigationText().contains(EXPECTED_QUALITY_ENGINEERING_BANNER_TEXT),
                "Subnavigation does not contain Quality Engineering item");

        qualityEngineeringPage.hoverServiceMenuItem();

        assertTrue(qualityEngineeringPage.getClassServiceMenu().contains(SUPPOSED_ACTIVE_ATTRIBUTE),
                "Service menu item is not active");
        assertTrue(qualityEngineeringPage.getClassQualityEngineeringMenu().contains(SUPPOSED_ACTIVE_ATTRIBUTE),
                "Quality Engineering Menu Item is not active");
    }

    @Test
    @DisplayName("[Expected to fail] Verify User can send Message from Automation Page")
    void tc2ShouldSendMessageViaContactUsFormFromAutomationPage() {
        homePage.goToAutomationMenuItem();
    }

    @Test
    @DisplayName("Verify User can send Message from Contact Us page")
    void tc2bShouldSendMessageViaContactUsFormFromAutomationPage() {
        ContactUsPage contactUsPage = homePage.goToContactUs();
        contactUsPage.populateMessageData();
        contactUsPage.confirmCollectingData();
        contactUsPage.sendMessage();
        assertTrue(contactUsPage.getMessageAfterSending().contains(EXPECTED_MESSAGE_AFTER_SUBMISSION),
                "Page does not contain expected text after sending message");

    }

    @ParameterizedTest
    @CsvFileSource(resources = CSV_PATH, numLinesToSkip = 1)
    @DisplayName("Verify User can navigate to Country-specific site")
    void tc3ShouldCountrySpecificSitesAvailable(String country, String url) {
        SubsidiaryPage subsidiaryPage = homePage.goToSubsidiarySite(country);
        assertThat(subsidiaryPage.getPage()).hasURL(url,
                new PageAssertions.HasURLOptions().setTimeout(config().waitForNewPage()));
    }
}
