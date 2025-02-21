package com.ui.pages;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void click(String selector){
        page.click(selector);
    }

}
