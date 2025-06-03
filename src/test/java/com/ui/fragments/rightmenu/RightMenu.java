package com.ui.fragments.rightmenu;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ui.fragments.rightmenu.rightmenupanels.*;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

import java.time.Duration;

@Getter
public class RightMenu extends BasePage {

    private final ObjectsInStorage objectsInStorage;
    private final ObjectsOnScene objectsOnScene;
    private final Properties properties;
    private final BindBusinessObjects bindBusinessObjects;
    private final Styles styles;

    private final Locator panelOfWindowsBut = page.locator("id=CircleOutlined");


    public RightMenu(Page page) {
        super(page);
        objectsInStorage = new ObjectsInStorage(page);
        objectsOnScene = new ObjectsOnScene(page);
        properties = new Properties(page);
        bindBusinessObjects = new BindBusinessObjects(page);
        styles = new Styles(page);
    }

    @Step("Нажать на объекты в хранилище")
    public RightMenu pressObjectsInStorageBtn(){
        page.click("id=objectsInTheStorage");
        return this;
    }

    @Step("Открытие/Закрытие Объекты сессии")
    public RightMenu pressObjectsOnStageButton() {
        page.click("id=objectsOnStage");
        return this;
    }

    @Step("Открытие/Закрытие Объекты сессии")
    public RightMenu pressPropertiesButton() {
        page.click("id=option");
        return this;
    }

    @Step("Открытие/Закрытие Объекты сессии")
    public RightMenu pressStylesButton() {
        page.click("id=stylesObject");
        return this;
    }

    @Step("Открытие/Закрытие правой панели окон")
    public RightMenu ClickPropertiesMenu() {
        panelOfWindowsBut.click();
        return this;
    }
}
