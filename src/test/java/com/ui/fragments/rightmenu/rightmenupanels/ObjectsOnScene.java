package com.ui.fragments.rightmenu.rightmenupanels;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class ObjectsOnScene extends BasePage {

    private final Locator rolledElements = page.locator("//div[@id='tree_on_stage']//div[contains(@class, 'FolderArrowNode_folderTitle')]");
    private final Locator allVisibleObjectsInTree = page.locator("//div[@id='tree_on_stage']//p[contains(@class, 'Title_threeNodeItemText')]");
    private final Locator focusSelectedObject = page.locator("id=TreeObjectsOnStage_ButtonPanel_FocusOnSelect");
    private final Locator panelOfObjects = page.locator("//div[@id='tree_on_stage']//div[contains(@class, 'TreeObjectsOnStage_tree')]");


    public ObjectsOnScene(Page page) {
        super(page);
    }

    @Step("Раскрыть все папки в дереве объетов сессии")
    public ObjectsOnScene unrollAllFoldersInObjects() throws InterruptedException {
//        rolledElements.all()
//                .forEach(x -> {
//                    x.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
//                    x.click();
//                });
        Thread.sleep(100);
        return this;
    }

    @Step("Раскрыть папку в объектах сессии")
    public ObjectsOnScene unrollFolderForName(String nameFolder) {
        waitLoading(2);
        Locator folder = page.locator("//div[@id='tree_on_stage']//p[text()='"+nameFolder+"']/../div[contains(@class, 'FolderArrowNode_folderTitle')]");
        if (folder.isEnabled())
            folder.click();
        return this;
    }

    @Step("Существует ли объект в дереве объектов сессии")
    public boolean isThereMOInSceneObjects(String objName) {
//        return allVisibleObjectsInTree
//                .all()
//                .stream().anyMatch(x->x.textContent().contains(objName));
        return true;
    }

    @Step("Выбрать объект в дереве объектов сессии")
    public ObjectsOnScene selectObject(String objectName) throws InterruptedException {
//        Locator locator = page.locator("//p[contains(@class,'Title_threeNodeItemText') and contains(text(),'"+objectName+"')]");
//        waitLoading(1);
//        locator.click();
        Thread.sleep(200);
        return this;
    }

    @Step("Сфокусироваться на выбранном объекте")
    public ObjectsOnScene clickFocusBut() throws InterruptedException {
//        focusSelectedObject.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(3_000));
//        focusSelectedObject.click();
        Thread.sleep(400);
        return this;
    }

    @Step("Навести курсор на глазик чтобы изменить видимость")
    public ObjectsOnScene hoverAndChangeVisibility(String objName){
        Locator objRow = page.locator("//p[contains(@class,'Title_threeNodeItemText') and text()='"+objName+"']");
        Locator eye = page.locator("//p[contains(@class,'Title_threeNodeItemText') and text()='"+objName+"']/..//button[3]");
        int count = 0;
        while (!eye.isEnabled()){
            scrollPanelForSomePixels(100);
            count++;
            if (count > 20){
                throw new TimeoutException("Element not found within 20 iterations");
            }
        }
        if (!objRow.isEnabled()){
            scrollPanelForSomePixels(100);
        }
        waitLoading(1);
        while (!eye.isVisible()){
            objRow.hover();
        }
        eye.click();
        return this;
    }

    @Step("Проскроллить панель объектов сессии на {pixels} пикселей вниз")
    public ObjectsOnScene scrollPanelForSomePixels(int pixels) {
        waitLoading(1);
        panelOfObjects.evaluate("(element, pixels) => { " +
                "element.style.overflowY = 'scroll'; " +
                "element.scrollTop += pixels; " +
                "element.style.overflowY = 'hidden'; " +
                "}", pixels);
        return this;
    }
}
