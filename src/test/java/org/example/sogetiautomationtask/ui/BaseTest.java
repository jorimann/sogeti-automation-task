package org.example.sogetiautomationtask.ui;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.ScreenshotType;
import io.qameta.allure.Step;
import org.example.sogetiautomationtask.reporting.AllureReporterManagement;
import org.example.sogetiautomationtask.reporting.TestListener;
import org.example.sogetiautomationtask.ui.pages.HomePage;
import org.example.sogetiautomationtask.ui.utils.BrowserManager;
import org.example.sogetiautomationtask.ui.factory.PageFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.Collections;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected HomePage homePage;

    @RegisterExtension
    TestListener listener = new TestListener();

    @BeforeAll
    public void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);
        AllureReporterManagement.storeAllureEnvironmentConfigurations();
    }

    @AfterAll
    public void closeBrowserAndPlaywrightSessions() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void setupContextAndNavigateToHomePage() {
        Browser.NewContextOptions options = new Browser.NewContextOptions()
                .setViewportSize(config().viewportWidth(),
                        config().viewportHeight())
                .setDeviceScaleFactor(config().deviceScaleFactor());

        context = browser.newContext(options);
        context.setDefaultTimeout(config().timeout());

        if (config().tracingEnabled()) {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
        }

        setupCookie();
        page = context.newPage();

        page.navigate(config().uiBaseUrl());

        homePage = PageFactory.createInstance(page, HomePage.class);
        listener.setPage(page);
    }

    @AfterEach
    void closeContext() {
        listener.takeScreenshot();

        if (config().tracingEnabled()) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }

        if (page != null) page.close();
        if (context != null) context.close();
    }

    @Step
    protected void takeScreenshot() {
        AllureReporterManagement.saveScreenshot(
                page.screenshot(new Page.ScreenshotOptions()
                        .setFullPage(true)
                        .setType(ScreenshotType.PNG)));
    }

    //'Accept All' cookie
    private void setupCookie() {
        String cookieValue = "{\"stamp\":\"cEEox2rhRKOB3DaFVTvYLl6A1DXNuIItcHsEdXJNdkqSZ32woN8kww==\",\"necessary\":true,\"preferences\":false,\"statistics\":false,\"marketing\":false,\"method\":\"explicit\",\"ver\":1,\"utc\":1733677145386,\"region\":\"de\"}";
        long expiryTime = System.currentTimeMillis() / 1000 + 3600;
        context.addCookies(Collections.singletonList(new Cookie("CookieConsent", cookieValue).setDomain("www.sogeti.com").setPath("/").setExpires(expiryTime)));
    }
}
