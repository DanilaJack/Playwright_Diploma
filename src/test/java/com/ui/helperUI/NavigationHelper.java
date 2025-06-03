package com.ui.helperUI;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.*;
import com.ui.fragments.StatusLine;
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
    public NavigationHelper mouseClickByOffsetFromViewport(int xOffset, int yOffset, String objId) throws InterruptedException {
//        Locator canvas = page.locator("id=" + objId);
//        canvas.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//
//        BoundingBox box = canvas.boundingBox();
//        if (box != null) {
//            page.mouse().click(box.x + xOffset, box.y + yOffset);
//        } else {
//            throw new RuntimeException("Bounding box не получен");
//        }
        Thread.sleep(100);
        return this;
    }


    @Step("Зум на сцене")
    public NavigationHelper zoomCanvas(int zoomOut) throws InterruptedException {
//        // Найдём <canvas id="canvas">
//        Locator canvas = page.locator("//canvas[@id='canvas']");
//
//        // Сформируем скрипт для dispatchEvent
//        String script = "canvas => {" +
//                "  const event = new WheelEvent('wheel', { deltaY: " + zoomOut + " });" +
//                "  canvas.dispatchEvent(event);" +
//                "}";
//
//        // Выполним JS над элементом
//        canvas.evaluate(script);
        Thread.sleep(200);

        return this;
    }

    @Step("Перемещаться попиксельно в соответствие с координатами курсора в статусной строке")
    public NavigationHelper moveToCoordinates(double targetX, double targetY, Page page) {
        StatusLine statusLine = new StatusLine(page);
        int largeStep = 25, mediumStep = 10, smallStep = 1;

        double currentX = Double.parseDouble(statusLine.getCoordinates().get(0));
        double currentY = Double.parseDouble(statusLine.getCoordinates().get(1));

        // Стартовая позиция мыши (например, из середины экрана)
        double cursorX = currentX;
        double cursorY = currentY;

        while (Math.abs(currentX - targetX) > 1 || Math.abs(currentY - targetY) > 1) {

            int stepX = chooseStep(currentX, targetX, largeStep, mediumStep, smallStep);
            int stepY = chooseStep(currentY, targetY, largeStep, mediumStep, smallStep);

            if (Math.abs(currentX - targetX) > 1) {
                cursorX += (currentX < targetX ? stepX : -stepX);
            }
            if (Math.abs(currentY - targetY) > 1) {
                cursorY += (currentY < targetY ? -stepY : stepY); // Важно: в некоторых UI Y идёт вниз
            }

            page.mouse().move(cursorX, cursorY);
            currentX = Double.parseDouble(statusLine.getCoordinates().get(0));
            currentY = Double.parseDouble(statusLine.getCoordinates().get(1));
        }

        return this;
    }

    private int chooseStep(double current, double target, int large, int medium, int small) {
        double delta = Math.abs(current - target);
        if (delta < medium) return small;
        if (delta < large) return medium;
        return large;
    }


}
