package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

public class ViewPanel extends BasePage {

    private final Locator sceneToolsMainBtn = page.locator("id=AreaSettings");
    private final Locator autoGridBtn = page.locator("//div[@id='AreaSettings']//span[text()='Автосетка']");

    private final Locator performanceBut = page.locator("id=PointsOfView");
    private final Locator planeTopBut = page.locator("id=PointOfView_Top");
    private final Locator planeBottomBut = page.locator("id=PointOfView_Bot");
    private final Locator toNorthEastBut = page.locator("id=PointOfView_NE");
    private final Locator toNorth = page.locator("id=PointOfView_North");

    private final Locator visualSettingBut = page.locator("//button[@id='VisualSetting']/../span"); // стрелка раскрыть меню кнопки Режим просмотра
    private final Locator settingSliceBut = page.locator("id=VisualSetting_slice");

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

    @Step("Выбор представления на сцене: {namePlane}")
    public ViewPanel settingCameraInPlaneTopAndBottom(String namePlane) {
        performanceBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        performanceBut.click();

        switch (namePlane) {
            case "Вид сверху":
                planeTopBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                planeTopBut.click();
                break;
            case "Вид снизу":
                planeBottomBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                planeBottomBut.click();
                break;
            case "На Северо-Восток":
                toNorthEastBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                toNorthEastBut.click();
                break;
            case "На Север":
                toNorth.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                toNorth.click();
                break;
        }
        return this;
    }

    @Step("Установка вида - срез 2D")
    public ViewPanel set2D_Slice() {
        visualSettingBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        visualSettingBut.click();
        waitLoading(1);
        settingSliceBut.click();
        return this;
    }

}
