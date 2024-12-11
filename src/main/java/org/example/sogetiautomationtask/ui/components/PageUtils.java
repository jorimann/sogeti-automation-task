package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import org.example.sogetiautomationtask.ui.utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtils {
    private static final Logger logger = LoggerFactory.getLogger(PageUtils.class);

    public static Locator waitForElement(Locator locator) {
        long startTime = System.currentTimeMillis();
        int timeout = 5000;
        int pollInterval = 200;

        while (System.currentTimeMillis() - startTime < timeout) {
            if (locator.isVisible() && locator.isEnabled()) {
                return locator;
            }
            try {
                Thread.sleep(pollInterval);
            } catch (InterruptedException e) {
                logger.error("element after await is still inactive. element: {}", locator, e.getMessage());
            }
        }
        return locator;
    }

    public static void waitForPageLoad(Page page) {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE,
                    new Page.WaitForLoadStateOptions()
                            .setTimeout(ConfigReader.getInt("ui.timeout.page.load")));
        } catch (TimeoutError error) {
            logger.warn("page load was not completed in defined timeout, but script attempts to run further", error.getMessage());
        }
    }
}
