package com.ui.fragments.panelmenu;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.fragments.panelmenu.panelmenutabs.SpatialDataImport;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

@Getter
public class PanelMenu extends BasePage {

    private final SpatialDataImport spatialDataImport;


    public PanelMenu(Page page) {
        super(page);
        spatialDataImport = new SpatialDataImport(page);
    }

    @Step("Нажатие на кнопку Файл")
    public PanelMenu pressFileBut() {
        waitLoading(1);
        Locator docMenuBtn = page.locator("id=DocMenu");
        docMenuBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        docMenuBtn.click();
        return this;
    }

}
