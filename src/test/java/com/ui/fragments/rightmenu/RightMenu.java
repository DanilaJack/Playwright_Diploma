package com.ui.fragments.rightmenu;

import com.microsoft.playwright.Page;
import com.ui.fragments.rightmenu.rightmenupanels.ObjectsInStorage;
import com.ui.fragments.rightmenu.rightmenupanels.ObjectsOnScene;
import com.ui.fragments.rightmenu.rightmenupanels.Properties;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class RightMenu extends BasePage {

    private final ObjectsInStorage objectsInStorage;
    private final ObjectsOnScene objectsOnScene;
    private final Properties properties;

    public RightMenu(Page page) {
        super(page);
        objectsInStorage = new ObjectsInStorage(page);
        objectsOnScene = new ObjectsOnScene(page);
        properties = new Properties(page);
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
}
