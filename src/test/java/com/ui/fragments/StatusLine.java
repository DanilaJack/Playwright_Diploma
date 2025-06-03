package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class StatusLine extends BasePage {

    private final Locator coordinates = page.locator("//span[contains(@class, 'TextStatusBar_label')]//following-sibling::span");

    public StatusLine(Page page) {
        super(page);
    }

    @Step("Получение координат привязки/курсора")
    public List<String> getCoordinates() {
        List<String> coordinateTexts = new ArrayList<>();
        int count = coordinates.count(); // получаем количество элементов

        for (int i = 0; i < count; i++) {
            String text = coordinates.nth(i).innerText().trim(); // или .textContent()
            coordinateTexts.add(text);
        }

        return coordinateTexts;
    }
}
