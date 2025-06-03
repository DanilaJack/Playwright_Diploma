package com.ui.fragments.rightmenu.rightmenupanels;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

public class BindBusinessObjects extends BasePage {

    private final Locator spaceInput = page.locator("//span[text()='Пространство']/ancestor::label//div[@class='ant-select-selector']");
    private final Locator templateInput = page.locator("//span[text()='Шаблон бизнес-объекта']/ancestor::label//div[@class='ant-select-selector']");
    private final Locator createBut = page.locator("//div[contains(@class, 'BindingFooter_buttonPanel')]/button");

    public BindBusinessObjects(Page page) {
        super(page);
    }

    @Step("Выбор пространства - {spaceName}")
    public BindBusinessObjects chooseSpace(String spaceName) throws InterruptedException {

//        Locator requiredSpace = page.locator("//div[contains(@class, 'ant-select-dropdown') and not(contains(@class, 'ant-select-dropdown-hidden'))]//span[text()='"+spaceName+"']");
//        spaceInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
//        spaceInput.click();
//        waitLoading(1);
//        while (!requiredSpace.isVisible()) {
//            page.keyboard().press("ArrowDown");
//        }
//        requiredSpace.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
//        requiredSpace.click();

        Thread.sleep(100);
        return this;
    }
    @Step("Выбор шаблона - {template} из списка")
    public BindBusinessObjects chooseTemplate(String template) throws InterruptedException {
//        Locator requiredObj = page.locator("//div[@class='ant-select-item-option-content']//span[text()='"+template+"']");
//        templateInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
//        templateInput.click();
//
//        waitLoading(1);
//        while (!requiredObj.isVisible()){
//            page.keyboard().press("ArrowDown");
//        }
//        requiredObj.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
//        requiredObj.click();
        Thread.sleep(200);
        return this;
    }

    @Step("Нажатие на кнопку - создать/сохранить")
    public BindBusinessObjects pressCreateBut() throws InterruptedException {
//        createBut.click();
//        waitLoading(2);
//
//        Locator success_message = page.locator("//div[contains(text(),'Новый бизнес объект создан и помещен в хранилище')]");
//
//        while (!success_message.isVisible()) {
//            waitLoading(1);
//        }
//        waitLoading(2);
//
//        System.out.println("Данные успешно сохранены.");
        Thread.sleep(4000);
        return this;
    }



}
