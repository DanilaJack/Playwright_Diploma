package com.ui.fragments.rightmenu.rightmenupanels;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

public class Properties extends BasePage {

    private final String attributeValueInput = "//div[contains(@class, 'PropertyMultiObject-module__name--')";
    private final Locator bindBut = page.locator("//div[contains(@class, 'BindingButtonBlock-module__')]/button[1]");
    private final Locator spaceInput = page.locator("//span[@class='ant-select-selection-item']/span[contains(@class, 'SelectWorkspace-module__option')]");
    private final Locator templateInput = page.locator("//span[contains(text(), 'Выберите шаблон бизнес-объекта')]/../span/input");
    private final Locator createBut = page.locator("//div[contains(@class, 'BindingFooter-module__buttonPanel')]/button");


    public Properties(Page page) {
        super(page);
    }

    @Step("Изменить значение атрибута - {attributeName}")
    public Properties changeAttributeValue(String attributeName, String attributeValue) throws InterruptedException {
        Locator input = page.locator(attributeValueInput + "and text()='" + attributeName + "']/../..//input");

        if (input.getAttribute("type").equals("checkbox")) {
            input.click();
        } else {
            input.click();
            page.keyboard().press("Control+A");
            page.keyboard().press("Backspace");
            input.fill(attributeValue);
            page.keyboard().press("Enter");
            waitLoading(1);
        }
        return this;
    }

    @Step("Открытие окна привязки БО в свойствах")
    public Properties openBind() {
        bindBut.click();
        return this;
    }

    @Step("Выбор пространства - {spaceName}")
    public Properties chooseSpace(String spaceName) throws InterruptedException {

        Locator requiredSpace = page.locator("//div[contains(@class, 'ant-select-dropdown') and not(contains(@class, 'ant-select-dropdown-hidden'))]//span[text()='"+spaceName+"']");
        spaceInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        spaceInput.click();

        while (!requiredSpace.isVisible()) {
            page.keyboard().press("Arrow_down");
        }
        requiredSpace.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        requiredSpace.click();

        return this;
    }

    @Step("Выбор шаблона - {template} из списка")
    public Properties chooseTemplate(String template) {
        Locator requiredObj = page.locator("//div[@class='ant-select-item-option-content']//span[text()='"+template+"']");
        templateInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        templateInput.click();

        while (!requiredObj.isVisible()){
            page.keyboard().press("Arrow_down");
        }
        requiredObj.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        requiredObj.click();
        return this;
    }

    @Step("Нажатие на кнопку - создать/сохранить")
    public Properties pressCreateBut() {
        createBut.click();
        waitLoading(2);

        Locator success_message = page.locator("//div[contains(text(),'Новый бизнес объект создан и помещен в хранилище')]");

        while (!success_message.isVisible()) {
            waitLoading(1);
        }
        waitLoading(2);

        System.out.println("Данные успешно сохранены.");

        return this;
    }
}
