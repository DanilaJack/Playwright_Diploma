package com.ui.fragments.rightmenu.rightmenupanels;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

public class ObjectsOnScene extends BasePage {

    private final Locator rolledElements = page.locator("//div[@id='DirectoryTree']//div/span[contains(@class, 'ant-tree-switcher_close')]/span");
    private final Locator allVisibleObjectsInTree = page.locator("//span[contains(@class, 'ant-tree-node-content-wrapper ant-tree-node-content-wrapper-normal')]//p[contains(@class, 'Title-module__threeNodeItemText')]");
    private final Locator focusSelectedObject = page.locator("id=TreeObjectsOnStage_ButtonPanel_FocusOnSelect");


    public ObjectsOnScene(Page page) {
        super(page);
    }

    @Step("Раскрыть все папки в дереве объетов сессии")
    public ObjectsOnScene unrollAllFoldersInObjects(){
        rolledElements.all()
                .forEach(x -> {
                    x.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
                    x.click();
                });
        return this;
    }

    @Step("Существует ли объект в дереве объектов сессии")
    public boolean isThereMOInSceneObjects(String objName) {
        return allVisibleObjectsInTree
                .all()
                .stream().anyMatch(x->x.textContent().contains(objName));
    }

    @Step("Выбрать объект в дереве объектов сессии")
    public ObjectsOnScene selectObject(String objectName) {
        Locator locator = page.locator("//div[contains(@class, 'ant-tree-treenode-switcher-close')]//p[contains(@class,'Title-module__threeNodeItemText') and contains(text(),'" + objectName + "')]");
        waitLoading(1);
        locator.click();
        return this;
    }

    @Step("Сфокусироваться на выбранном объекте")
    public ObjectsOnScene clickFocusBut() {
        focusSelectedObject.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(3_000));
        focusSelectedObject.click();
        return this;
    }
}
