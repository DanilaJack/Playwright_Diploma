package com.ui.helperUI;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.*;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.util.Arrays;

public class NavigationHelper extends BasePage {

    public NavigationHelper(Page page) {
        super(page);
    }

    @Step("Клик ПКМ по координатам {xOffset}, {yOffset}")
    public NavigationHelper clickRightButMouse(int xOffset,int yOffset,String ObjXpathByID){
        Locator element = page.locator("canvas");

        BoundingBox box = element.boundingBox();

        if (box != null){
            int x = (int) (box.x+xOffset);
            int y = (int) (box.y+yOffset);

            page.mouse().move(x, y);
            page.mouse().click(x, y, new Mouse.ClickOptions().setButton(MouseButton.RIGHT));
        }
        else {
            throw new RuntimeException("Элемент не найден");
        }
        return this;
    }

    @Step("Зажатие клавиши {clamping}, нажатие клавиши {press}")
    public void clampingAndPressKeyboardButtons(String clamping, String press) {
        page.keyboard().down(clamping);
        page.keyboard().press(press);
        page.keyboard().up(clamping);
    }

    @Step("Зажатие клавиши {clamping}")
    public void clampingKeyboardButtons(String clamping) {
        page.keyboard().down(clamping);
    }

    @Step("Отпускание клавиши {clamping}")
    public void releaseKeyboardButtons(String clamping) {
        page.keyboard().up(clamping);
    }

    @Step("Нажатие клавиши {keys}")
    public void pressKeyboardButton(String keys) {
        page.keyboard().press(keys);
    }

    @Step("Клик по координатам {xOffset}, {yOffset}")
    public NavigationHelper mouseClickByOffsetFromViewport(int xOffset, int yOffset, String objId) {
        Locator canvas = page.locator("id=" + objId);
        canvas.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        BoundingBox box = canvas.boundingBox();
        if (box != null) {
            page.mouse().click(box.x + xOffset, box.y + yOffset);
        } else {
            throw new RuntimeException("Bounding box не получен");
        }
        return this;
    }


    @Step("Зум на сцене")
    public NavigationHelper zoomCanvas(int zoomOut) {
        // Найдём <canvas id="canvas">
        Locator canvas = page.locator("//canvas[@id='canvas']");

        // Сформируем скрипт для dispatchEvent
        String script = "canvas => {" +
                "  const event = new WheelEvent('wheel', { deltaY: " + zoomOut + " });" +
                "  canvas.dispatchEvent(event);" +
                "}";

        // Выполним JS над элементом
        canvas.evaluate(script);

        return this;
    }


}
