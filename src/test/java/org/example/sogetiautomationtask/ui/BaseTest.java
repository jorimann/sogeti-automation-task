package org.example.sogetiautomationtask.ui;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import io.qameta.allure.Attachment;
import org.example.sogetiautomationtask.reporting.AllureReporterManagement;
import org.example.sogetiautomationtask.ui.components.PageUtils;
import org.example.sogetiautomationtask.ui.pages.HomePage;
import org.example.sogetiautomationtask.ui.utils.BrowserManager;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.Collections;

import static org.example.sogetiautomationtask.config.ConfigurationManager.config;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected HomePage homePage;

    @BeforeAll
    public static void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);
        AllureReporterManagement.storeAllureEnvironmentConfigurations();
    }

    @AfterAll
    public static void closeBrowserAndPlaywrightSessions() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    //'Accept All' cookie
    private void setupCookie() {
        String cookieValue = "{\"stamp\":\"cEEox2rhRKOB3DaFVTvYLl6A1DXNuIItcHsEdXJNdkqSZ32woN8kww==\",\"necessary\":true,\"preferences\":false,\"statistics\":false,\"marketing\":false,\"method\":\"explicit\",\"ver\":1,\"utc\":1733677145386,\"region\":\"de\"}";
        long expiryTime = System.currentTimeMillis() / 1000 + 3600;
        context.addCookies(Collections.singletonList(new Cookie("CookieConsent", cookieValue).setDomain("www.sogeti.com").setPath("/").setExpires(expiryTime)));
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
        PageUtils.waitForPageLoad(page);

        homePage = new HomePage(page);
    }

    @AfterEach
    void closeContext() {
        if (config().tracingEnabled()) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }

        if (page != null) {
            AllureReporterManagement.saveScreenshot(takeScreenshot());
            page.close();
        }
        if (context != null) context.close();
    }

    public byte[] takeScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
}
