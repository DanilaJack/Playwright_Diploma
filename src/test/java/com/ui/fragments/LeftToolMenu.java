package com.ui.fragments;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class LeftToolMenu extends BasePage {

    private final Locator openTriangularListBtn = page.locator("//div[contains(@class, 'AreaTriangular')]//span[contains(@class, 'ButtonWithPanel_triangleWrapper')]");
    private final Locator digitalSurfaceBtn = page.locator("id=tool_panel_dtm_creator_tool");

    private final Locator openSheetsListBtn = page.locator("id=panel_sheet_canvas");
    private final Locator sheetOpts = page.locator("//div[contains(@class, 'CanvasSheetButton_option')]/button/following-sibling::span[1]");
    private final Locator addFragmentBtn = page.locator("id=panel_fragment_canvas");

    private final Locator pointBtn = page.locator("id=tool_panel_point_creator_tool");
    private final Locator cutBtn = page.locator("id=tool_panel_section_drafter_tool");

    private final Locator openFiguresListBtn = page.locator("//button[@id='toolPanelFigure']/following-sibling::span");
    private final Locator rectangleBtn = page.locator("id=tool_panel_rectangle_creator_tool");


    public LeftToolMenu(Page page) {
        super(page);
    }

    @Step("Выбрать инструмент построения Точки")
    public LeftToolMenu createPoint(){
        pointBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
        pointBtn.click();
        return this;
    }

    @Step("Выбрать инструмент создания цифровой поверхности модели")
    public LeftToolMenu createDigitalSurface(){
        openTriangularListBtn.click();
        waitLoading(1);
        digitalSurfaceBtn.click();
        return this;
    }

    @Step("Выбрать добавление Листа на Холст")
    public LeftToolMenu addNewListToCanvas(String opt){
        openSheetsListBtn.click();
        waitLoading(1);
        sheetOpts.all().stream().filter(x -> x.textContent().equals(opt)).findFirst().orElseThrow(() -> new NoSuchElementException("Элемент не найден")).click();
        return this;
    }

    @Step("Выбрать добавление Фрагмента на Холст")
    public LeftToolMenu addNewFragmentToCanvas(){
        addFragmentBtn.click();
        return this;
    }

    @Step("Выбрать инструмент создания Разреза")
    public LeftToolMenu createCut(){
        cutBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
        cutBtn.click();
        return this;
    }

    @Step("Выбрать инструмент построения Прямоугольник (Полилинии)")
    public LeftToolMenu createRectangle(){
        openFiguresListBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2_000));
        openFiguresListBtn.click();
        waitLoading(1);
        rectangleBtn.click();
        return this;
    }
}
