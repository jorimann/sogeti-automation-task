package org.example.sogetiautomationtask.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Cookie;
import org.example.sogetiautomationtask.ui.components.PageUtils;
import org.example.sogetiautomationtask.ui.pages.HomePage;
import org.example.sogetiautomationtask.ui.utils.AllureAttachments;
import org.example.sogetiautomationtask.ui.utils.BrowserManager;
import org.example.sogetiautomationtask.ui.utils.ConfigReader;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected HomePage homePage;

    @BeforeAll
    public static void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);
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
    void setupContextAndNavigateToHomePage() throws InterruptedException {
        context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(ConfigReader.getInt("ui.viewport.width")
//                        , ConfigReader.getInt("ui.viewport.height"))
                .setDeviceScaleFactor(1));

        context.setDefaultTimeout(ConfigReader.getInt("ui.timeout"));

//                context.tracing().start(new Tracing.StartOptions()
//                .setScreenshots(true)
//                .setSnapshots(true));

        setupCookie();
        page = context.newPage();

        page.navigate(ConfigReader.get("ui.baseurl"));
        PageUtils.waitForPageLoad(page);

        homePage = new HomePage(page);
    }

    @AfterEach
    void closeContext() {

//        context.tracing().stop(new Tracing.StopOptions()
//                .setPath(Paths.get("trace2.zip")));

        if (page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            AllureAttachments.saveScreenshot(screenshot);
            page.close();
        }
        if (context != null) context.close();
    }
}
