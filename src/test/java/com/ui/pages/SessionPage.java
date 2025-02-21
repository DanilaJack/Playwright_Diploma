package com.ui.pages;


import com.microsoft.playwright.Page;

public class SessionPage extends BasePage {

    public SessionPage(Page page) {
        super(page);
    }

    public String getTitle() {
        return page.title();
    }
}
