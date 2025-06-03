package com.ui.fragments;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import com.ui.settings.ProjectSettings;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TableView extends BasePage {

    private final Locator namesOfGOAttrList = page.locator("//div[contains(@class, 'ggis-table-MainContent-module__tableHeadCellText-')]");
    private final Locator backToSceneModeBut = page.locator("//button/div[text()='Вернуться на сцену']");
    private final Locator table = page.locator("//article[contains(@class, 'TableContainer')]");
    private final Locator exportBtn = page.locator("//div[contains(@class, 'table-buttons')]/button");


    public TableView(Page page) {
        super(page);
    }

    @Step("Открытие нужной вкладки мультиобъекта в табличном представление")
    public TableView clickOnNeededTabOfMultiobject(String entity){
        waitLoading(1);
        Locator locator = page.locator("//div[contains(@class, 'ant-radio-group')]//span"+"[text()='"+entity+"']");
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        locator.click();
        return this;
    }

    @Step("Выделить столбец в таблице")
    public TableView highlightColumn(String columnName){
        namesOfGOAttrList.all().stream()
                .filter(x->x.textContent().equals(columnName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .click();
        return this;
    }

    @Step("Возвращение в режим сцены из табличного представления")
    public TableView clickOnBackToSceneModeBut(){
        backToSceneModeBut.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        backToSceneModeBut.click();
        return this;
    }

    @Step("Получение общее количество элементов во всех геометриях в МО")
    public int getFullAmountOfElements(){

        waitLoading(1);
        int current_amount = 0;
        List<String> current_numbers = new ArrayList<>();

        List<Locator> accessed_rows = page.locator("//tbody//tr/td[@data-type='row_select']//div[@data-value='row-number']").all();

        current_numbers = accessed_rows.stream().map(Locator::textContent).toList();
        current_amount = current_numbers.size();

        do{
           waitLoading(2);
            table.evaluate("element => element.scrollTop += 500");
            waitLoading(2);

            int finalCurrent_amount = current_amount;

            current_numbers = accessed_rows.stream()
                    .map(Locator::textContent)
                    .filter(x -> Integer.parseInt(x) > finalCurrent_amount)
                    .toList();

            current_amount += current_numbers.size();
        } while (!current_numbers.isEmpty());

        return current_amount;
    }

    @Step("Получение общего количества элементов во всех геометриях в МО")
    public int getFullAmountOfElements2() {
        int currentAmount = 0;
        List<String> currentNumbers;

        Locator accessedRows = page.locator("//tbody//tr/td[@data-type='row_select']//div[@data-value='row-number']");

        currentNumbers = accessedRows.allTextContents();
        currentAmount = currentNumbers.size();

        page.waitForTimeout(4000);

        do {
            // Получаем элемент DOM из локатора
            Locator table = page.locator("//article[contains(@class, 'TableContainer')]");
            ElementHandle tableElement = table.elementHandle(); // <-- добавлено

            if (tableElement != null) {
                // Прокручиваем вниз
                page.evaluate("el => el.scrollTop += 500", tableElement);
            }

            page.waitForTimeout(1000);

            List<String> allNumbers = accessedRows.allTextContents();

            int finalCurrentAmount = currentAmount;
            List<String> newNumbers = allNumbers.stream()
                    .filter(n -> {
                        try {
                            return Integer.parseInt(n.replaceAll("[^\\d]", "")) > finalCurrentAmount;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    })
                    .toList();

            currentAmount += newNumbers.size();
            currentNumbers = newNumbers;

        } while (!currentNumbers.isEmpty());

        return currentAmount;
    }

    @Step("Экспорт данных из таблицы в excel")
    public TableView exportDataFromTable(){

        Download download = page.waitForDownload(() -> {
            page.locator("//div[contains(@class, 'table-buttons')]/button").click();
        });
        download.saveAs(Paths.get(ProjectSettings.DOWNLOAD_PATH, download.suggestedFilename()));
        return this;
    }

    @Step("Воспользоваться глобальным поиском в табличном представление")
    public TableView globalSearch(String value){
        page.locator("//div[contains(@class, 'GlobalFilterComponent')]/button").click();
        waitLoading(1);
        page.locator("//div[contains(@class, 'GlobalFilterComponent')]//input").fill(value);
        page.keyboard().press("Enter");
        waitLoading(1);
        return this;
    }

    @Step("Редактировать ячейку таблицы по имени ГО")
    public TableView editCellInTable(String nameGO, String column, String value) {
        // Находим нужную ячейку по тексту в колонке "Имя ГО" и целевому столбцу
        Locator cell = page.locator("//td[@data-type='Имя ГО']/span[contains(text(), '" + nameGO + "')]")
                .locator("xpath=../../td[@data-type='" + column + "']");

        // Двойной клик по ячейке
        cell.dblclick();

        // Ждём появления поля ввода внутри ячейки (если оно появляется)
        page.waitForTimeout(1000); // waitLoading(1) аналогично

        // Выделяем всё и удаляем
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");

        // Вводим новое значение
        page.keyboard().type(value);

        // Подтверждаем Enter'ом
        page.keyboard().press("Enter");

        return this;
    }
}
