package org.example.sogetiautomationtask.ui.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;

public enum BrowserFactory {

    CHROMIUM {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.chromium().launch(options());
        }
    },
    FIREFOX {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.firefox().launch(options());
        }
    },
    WEBKIT {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.webkit().launch(options());
        }
    };

    public LaunchOptions options() {
        return new LaunchOptions()
                .setHeadless(ConfigReader.getBoolean("ui.headless"))
                .setSlowMo(ConfigReader.getInt("ui.slow.motion"));
//                .setArgs(Arrays.stream(new String[] { "--start-maximized" }).toList());
    }

    public abstract Browser createInstance(final Playwright playwright);
}
