package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class LeftToolMenu extends BasePage {

    private final Locator pointBtn = page.locator("id=tool_panel_point_creator_tool");

    public LeftToolMenu(Page page) {
        super(page);
    }

    @Step("Выбрать инструмент построения Точки")
    public LeftToolMenu createPoint(){
        pointBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
        pointBtn.click();
        return this;
    }


}
