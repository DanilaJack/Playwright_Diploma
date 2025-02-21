package com.ui.fragments.rightmenu;

import com.microsoft.playwright.Page;
import com.ui.fragments.rightmenu.rightmenupanels.ObjectsInStorage;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class RightMenu extends BasePage {

    private final ObjectsInStorage objectsInStorage;

    public RightMenu(Page page) {
        super(page);
        objectsInStorage = new ObjectsInStorage();
    }

    @Step("Нажать на объекты в хранилище")
    public RightMenu pressObjectsInStorageBtn(){
        page.click("id=objectsInTheStorage");
        return this;
    }
}
