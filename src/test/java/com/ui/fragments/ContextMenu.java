package com.ui.fragments;

import com.microsoft.playwright.Page;
import com.ui.pages.BasePage;

public class ContextMenu extends BasePage {

    public ContextMenu(Page page) {
        super(page);
    }

    public ContextMenu clickOnContextMenuOption(String option){
        page.locator("//span[contains(text(), '" + option + "')]").click();
        return this;
    }
}
