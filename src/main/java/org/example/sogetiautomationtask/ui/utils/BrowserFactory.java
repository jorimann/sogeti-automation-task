package org.example.sogetiautomationtask.ui.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;
import static org.example.sogetiautomationtask.config.ConfigurationManager.config;
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
                .setHeadless(config().headless())
                .setSlowMo(config().slowMotion());
//                .setArgs(Arrays.stream(new String[] { "--start-maximized" }).toList());
    }

    public abstract Browser createInstance(final Playwright playwright);
}
