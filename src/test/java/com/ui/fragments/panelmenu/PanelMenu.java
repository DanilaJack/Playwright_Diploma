package com.ui.fragments.panelmenu;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.fragments.panelmenu.panelmenutabs.SpatialDataImport;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class PanelMenu extends BasePage {

    private final SpatialDataImport spatialDataImport;

    private final Locator moveToCanvasBtn = page.locator("id=enter-to-canvas");
    private final Locator moveToSceneBtn = page.locator("//button[@id='editing']/preceding-sibling::button[1]");

    private final Locator exportFromCanvas = page.locator("//div[contains(@class, 'NavMenuContentItem_navContent') and text()='Экспорт объектов холста в *.dxf']");


    public PanelMenu(Page page) {
        super(page);
        spatialDataImport = new SpatialDataImport(page);
    }

    @Step("Нажатие на кнопку Файл")
    public PanelMenu pressFileBut() {
        waitLoading(1);
        Locator docMenuBtn = page.locator("id=docMenu");
        docMenuBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        docMenuBtn.click();
        return this;
    }

    @Step("Нажатие на кнопку 'Перейти в Холст со сцены 3d'")
    public PanelMenu pressMoveToCanvas(){
        moveToCanvasBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        moveToCanvasBtn.click();
        return this;
    }

    @Step("Нажатие на кнопку 'Перейти на сцену 3d с Холста'")
    public PanelMenu pressMoveToScene(){
        moveToSceneBtn.click();
        return this;
    }

    @Step("Нажать на экспорт объектов с холста")
    public PanelMenu pressExportFromCanvas(){
        exportFromCanvas.click();
        return this;
    }

}
