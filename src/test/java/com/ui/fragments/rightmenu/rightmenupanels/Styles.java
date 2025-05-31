package com.ui.fragments.rightmenu.rightmenupanels;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class Styles extends BasePage {

    String openPopOver = "//div[contains(@class, 'ant-popover ') and not(contains(@class, 'ant-popover-hidden'))]";


    private final Locator textMarkBtn = page.locator("//div[contains(@class, 'TextSettingGroupElement_button')]/button");
    private final Locator markTextArea = page.locator(openPopOver+"//textarea");
    private final Locator acceptMarkTextBtn = page.locator(openPopOver+"//button[contains(@class, 'accept')]");
    private final Locator markEye = page.locator("//div[contains(@class, 'ElementOfStylePanel')]/button");

    public Styles(Page page) {
        super(page);
    }

    @Step("Изменить/Установить текст текстовой метки")
    public Styles setMarkText(String markText) {
        waitLoading(1);
        textMarkBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        textMarkBtn.click();

        waitLoading(1);
        markTextArea.click();
        page.keyboard().down("Control");
        page.keyboard().press("A");
        page.keyboard().up("Control");
        page.keyboard().press("Back_space");
        markTextArea.fill(markText);

        acceptMarkTextBtn.click();
        return this;
    }

    @Step("Изменить видимость текстовой метки")
    public Styles changeMarkVisibility(){
        markEye.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        markEye.click();
        return this;
    }

}
