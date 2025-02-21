package com.ui.browsersettings;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ui.config.ConfigurationManager.config;

public enum BrowserFactory {

    CHROMIUM {
        @Override
        public Browser createInstance(final Playwright playwright){
            return playwright.chromium().launch(options());
        }
    },

    FIREFOX {
        @Override
        public Browser createInstance(final Playwright playwright){
            return playwright.firefox().launch(options());
        }
    },

    WEBKIT {
        @Override
        public Browser createInstance(final Playwright playwright){
            return playwright.webkit().launch(options());
        }
    };

    public BrowserType.LaunchOptions options(){

        return new BrowserType.LaunchOptions()
                .setArgs(List.of("--start-maximized"))
                .setHeadless(config().headless())
                .setSlowMo(config().slowMotion());
    }

    public abstract Browser createInstance(final Playwright playwright);
}
