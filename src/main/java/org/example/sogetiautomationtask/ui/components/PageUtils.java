package org.example.sogetiautomationtask.ui.components;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.example.sogetiautomationtask.config.ConfigurationManager.config;

public class PageUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtils.class);

    public static void waitForPageLoad(Page page) {
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE,
                    new Page.WaitForLoadStateOptions()
                            .setTimeout(config().timeoutPageLoad()));
        } catch (TimeoutError error) {
            LOGGER.warn("page load was not completed in defined timeout, but script attempts to run further", error.getMessage());
        }
    }
}
