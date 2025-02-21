package com.ui.pages;

import com.microsoft.playwright.Page;
import com.ui.fragments.rightmenu.RightMenu;
import lombok.Getter;

@Getter
public class ScenePage extends BasePage{

    private final RightMenu rightmenu;

    public ScenePage(Page page) {
        super(page);
        rightmenu = new RightMenu(page);
    }
}
