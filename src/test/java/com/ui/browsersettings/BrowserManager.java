package com.ui.browsersettings;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import static com.ui.config.ConfigurationManager.config;

public class BrowserManager {

    public static Browser getBrowser(Playwright playwright){
        return BrowserFactory.valueOf(config().browser().toUpperCase()).createInstance(playwright);
    }
}
