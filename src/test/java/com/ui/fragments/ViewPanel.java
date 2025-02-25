package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

public class ViewPanel extends BasePage {

    private final Locator sceneToolsMainBtn = page.locator("id=AreaSettings");
    private final Locator autoGridBtn = page.locator("//div[@id='AreaSettings']//span[text()='Автосетка']");



    public ViewPanel(Page page) {
        super(page);
    }

    @Step("Включение/отключение автосетки")
    public ViewPanel turnOnAutoGrid() {
        sceneToolsMainBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(2_000));
        sceneToolsMainBtn.click();
        waitLoading(1);
        autoGridBtn.click();
        return this;
    }

}
