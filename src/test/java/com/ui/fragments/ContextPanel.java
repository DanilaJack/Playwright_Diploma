package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

public class ContextPanel extends BasePage {

    private final Locator enterToEditMode = page.locator("id=panel_enterEditMode");


    public ContextPanel(Page page) {
        super(page);
    }

    @Step("Войти в режим редактирование мультиобъекта")
    public ContextPanel enterEditModeOfMO() {
        enterToEditMode.waitFor(new Locator.WaitForOptions().setTimeout(3_000));
        enterToEditMode.click();
        return this;
    }
}
