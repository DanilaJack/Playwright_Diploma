package com.ui.fragments;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import com.ui.settings.ProjectSettings;
import io.qameta.allure.Step;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TableView extends BasePage {

    private final Locator namesOfGOAttrList = page.locator("//div[contains(@class, 'ggis-table-MainContent-module__tableHeadCellText-')]");
    private final Locator backToSceneModeBut = page.locator("//button/div[text()='Вернуться на сцену']");
    private final Locator table = page.locator("//div[contains(@class, 'ggis-table-TableRootWrapper-module__table-wrapper')]");
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

        int current_amount = 0;
        List<String> current_numbers = new ArrayList<>();

        List<Locator> accessed_rows = page.locator("//tbody//tr/td[@data-type='row_select']//div[@data-value='row-number']").all();

        current_numbers = accessed_rows.stream().map(Locator::textContent).toList();
        current_amount = current_numbers.size();

        do{
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

    @Step("Экспорт данных из таблицы в excel")
    public TableView exportDataFromTable(){

        Download download = page.waitForDownload(() -> {
            page.locator("//div[contains(@class, 'table-buttons')]/button").click();
        });
        download.saveAs(Paths.get(ProjectSettings.DOWNLOAD_PATH, download.suggestedFilename()));
        return this;
    }
}
