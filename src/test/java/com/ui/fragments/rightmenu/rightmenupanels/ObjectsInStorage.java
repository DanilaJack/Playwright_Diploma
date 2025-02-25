package com.ui.fragments.rightmenu.rightmenupanels;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

public class ObjectsInStorage extends BasePage {

    private final Locator spaceListBut = page.locator("//div[contains(@class,'TreeHeader-module__buttonWrapper')]/button[1]");
    private final Locator applyBut = page.locator("//div[not(contains(@class, 'FilterModal'))]/button/span[text()='Применить']");
    private final Locator spaceBut = page.locator("//div[contains(@class, 'TreeHeader-module__selectItemText')]");
    private final Locator allElementsInStorage = page.locator("//div[contains(@class, 'ant-tree-treenode-switcher-close')]//span[contains(@class, 'threeNodeItemTitle--')]");
    private final Locator NetBut = page.locator("//div[text()='Сети']");
    private final Locator eyeButs = page.locator("//div[contains(@class,'TreeNodeTitle-module__buttonWrapper')]/button[2]");




    public ObjectsInStorage(Page page) {
        super(page);
    }

    @Step("Открытие списка пространств")
    public ObjectsInStorage pressSpaceButton() {
        spaceListBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        spaceListBut.click();
        return this;
    }

    @Step("Подключение пространства - {spaceName}")
    public  ObjectsInStorage connectSpace(String spaceName) {
        Locator requiredSpace = page.locator("//div[text()='" + spaceName + "']/../../label");
        Locator checked = page.locator("//div[contains(text(),'" + spaceName + "')]/../../label[contains(@class, 'checked')]");

        waitLoading(1);
        if (!checked.isVisible()) {
            requiredSpace.click();
        }
        applyBut.click();
        return this;
    }

    @Step("Выбор пространства - {spaceName}")
    public ObjectsInStorage setSpace(String spaceName) {
        Locator requiredSpace = page.locator("//div[contains(@class, 'TreeHeader-module__selectItem--')]//div[text()='" + spaceName + "']");
        spaceBut.hover();
        spaceBut.click();
        waitLoading(1);
        requiredSpace.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
        requiredSpace.click();
        waitLoading(2);
        return this;
    }

    @Step("Развернуть все папки в объектах хранилища")
    public ObjectsInStorage unrollAllFoldersInStorage() {
        waitLoading(1);
        Locator folders = page.locator("//div[@id='LocalTree_TreeWrapper']//span[@class='ant-tree-switcher ant-tree-switcher_close']");
        while (!folders.all().isEmpty()) {
            folders.all().getFirst().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
            folders.all().getFirst().click();
        }
        return this;
    }

    @Step("Проверка, что объект есть в объектах хранилища")
    public boolean isThereAnObjectInStorage(String objName){
        return allElementsInStorage.all().stream().anyMatch(x->x.textContent().contains(objName));
    }

    @Step("Открытие Сетей")
    public ObjectsInStorage pressNetBut() {
        NetBut.click();
        return this;
    }

    @Step("Нажатие на кнопку - глаз")
    public ObjectsInStorage pressEyeButton(String objName) {
        waitLoading(2);
        Locator net = page.locator("//span[contains(@class, 'threeNodeItemTitle-') and text()='"+objName+"']");
        Locator eye = net.locator("../../..//button[2]");
        net.hover();
        eye.click();
        return this;
    }

}
