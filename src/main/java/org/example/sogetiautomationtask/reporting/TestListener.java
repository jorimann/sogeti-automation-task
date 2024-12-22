package org.example.sogetiautomationtask.reporting;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListener implements TestWatcher {
    private Page page;
    private byte[] screenshot;

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (screenshot != null) {
            AllureReporterManagement.saveScreenshot(screenshot);
        }
    }

    public void takeScreenshot() {
        if (page != null && !page.isClosed()) {
            screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true)
                    .setType(ScreenshotType.PNG));
        }
    }
}
