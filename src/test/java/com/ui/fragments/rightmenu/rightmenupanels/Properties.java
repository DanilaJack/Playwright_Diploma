package com.ui.fragments.rightmenu.rightmenupanels;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;


public class Properties extends BasePage {

    private final String attributeValueInput = "//div[contains(@class, 'PropertyMultiObject-module__name--')";
    private final Locator bindBut = page.locator("//div[contains(@class, 'BindingButtonBlock')]/button[1]");
    private final Locator toggleToFillingGroup = page.locator("//div[contains(@class, 'PropertyListControlButtons_segmented')]//label[not(contains(@class, 'selected'))]");
    private final Locator selectObjTypeBtn = page.locator("//div[contains(@class, 'PropertyMultiObject_selectType')]");
    private final Locator attrNameInput = page.locator("//input[@placeholder='Введите имя атрибута']");
    private final Locator attrValueInput = page.locator("//input[@placeholder='Введите значение']");
    private final Locator saveEditAttrBtn = page.locator("//button[contains(@class, 'PropertyMultiObject_saveBtn')]");

    private final Locator addNewFillingGroupForPMBtn = page.locator("//button/span[text()='Добавить группу свойств']");

    private final String dropDownEl = "//div[contains(@class, 'ant-select-dropdown ') and not(contains(@class, 'ant-select-dropdown-hidden'))]";


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

    @Step("Переключиться на другую вкладку -> в группу свойств для наполнения или для объекта")
    public Properties switchToOtherTabForFilling() {
        toggleToFillingGroup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        toggleToFillingGroup.click();
        return this;
    }

    @Step("Клик на кнопку добавления новой группы свойств для наполнения Полигональной модели")
    public Properties clickAddNewGroupOfPropsForPM() {
        addNewFillingGroupForPMBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        addNewFillingGroupForPMBtn.click();
        return this;
    }


    @Step("Изменить/присвоить имя группы свойств наполнения")
    public Properties giveNameToGroupOfProps(String groupName) {
        Locator nameField = page.locator("//span[contains(@class, 'PropertyGroupName') and contains(@class, 'inputSimple')]");
        nameField.dblclick();

        Locator input = nameField.locator("xpath=./input");
        input.clear();
        input.fill(groupName);
        page.keyboard().press("Enter");

        return this;
    }

    @Step("Добавить новое свойство в группу свойств наполнения")
    public Properties addNewPropToGroupOfProps(String group, String type, String name, String value) {

        Locator groupSegment = page.locator("//span[text()='" + group + "']/ancestor::div[contains(@class, 'ant-collapse-item')][1]");
        groupSegment.locator("xpath=.//button[contains(@class, 'PropertiesGroupPanel_editBtn')]/span[contains(text(), 'Добавить свойство')]").click();

        waitLoading(1);
        selectObjTypeBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        selectObjTypeBtn.click();

        waitLoading(1);
        page.locator(dropDownEl + "//span[text()='" + type + "']").click();

        attrNameInput.clear();
        attrNameInput.fill(name);

        waitLoading(1);
        if (!value.isEmpty()) {
            attrValueInput.clear();
            attrValueInput.fill(value);
        }
        saveEditAttrBtn.click();

        return this;
    }
}
