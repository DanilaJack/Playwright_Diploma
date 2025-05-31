package com.ui.helperUI;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ParameterizedDesign extends BasePage {

    private final Locator axisX = page.locator("id=ParameterizationInput_x");
    private final Locator axisY = page.locator("id=ParameterizationInput_y");
    private final Locator axisZ = page.locator("id=ParameterizationInput_z");

    private final Locator lengthInput = page.locator("id=ParameterizationInput_length");
    private final Locator azimuthInput = page.locator("id=ParameterizationInput_azimuth");
    private final Locator angleInput = page.locator("id=ParameterizationInput_angle");


    public ParameterizedDesign(Page page) {
        super(page);
    }

    @Step("Установка значения координаты X")
    public ParameterizedDesign setAxisX(String axisX) throws InterruptedException {
        this.axisX.fill(axisX);
        return this;
    }

    @Step("Установка значения координаты Y")
    public ParameterizedDesign setAxisY(String axisY) throws InterruptedException {
        this.axisY.fill(axisY);
        return this;
    }

    @Step("Установка значения координаты Z")
    public ParameterizedDesign setAxisZ(String axisZ) throws InterruptedException {
        this.axisZ.fill(axisZ);
        return this;
    }

    @Step("Указать угол для параметра трансформации")
    public ParameterizedDesign setAngle(String angle){
        waitLoading(1);
        angleInput.fill(angle);
        return this;
    }

    @Step("Указать азимут для параметра трансформации")
    public ParameterizedDesign setAzimuth(String azimuth){
        waitLoading(1);
        azimuthInput.fill(azimuth);
        return this;
    }

    @Step("Указать длину для параметра трансформации")
    public ParameterizedDesign setLength(String length){
        waitLoading(1);
        lengthInput.fill(length);
        return this;
    }

}
