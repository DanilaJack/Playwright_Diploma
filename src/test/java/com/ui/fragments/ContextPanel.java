package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class ContextPanel extends BasePage {

    private final Locator enterToEditMode = page.locator("id=panel_enterEditMode");
    private final Locator polyhedralOperationsBtn = page.locator("id=polyhedral_surface_operation_button");
    private final Locator volumeBetweenSurfacesBtn = page.locator("id=volume_surfaces_button");

    private final Locator moveToCutViewBut = page.locator("id=section_enter_view");


    public ContextPanel(Page page) {
        super(page);
    }

    @Step("Войти в режим редактирование мультиобъекта")
    public ContextPanel enterEditModeOfMO() {
        enterToEditMode.waitFor(new Locator.WaitForOptions().setTimeout(3_000));
        enterToEditMode.click();
        return this;
    }

    @Step("Расчет объема между поверхностями")
    public ContextPanel pressVolumeBetweenSurfaces() {
        polyhedralOperationsBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        polyhedralOperationsBtn.click();
        waitLoading(2);
        volumeBetweenSurfacesBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        volumeBetweenSurfacesBtn.click();
        return this;
    }

    @Step("Перейти в разрез")
    public ContextPanel moveToCutView() {
        moveToCutViewBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        moveToCutViewBut.click();
        return this;
    }
}
