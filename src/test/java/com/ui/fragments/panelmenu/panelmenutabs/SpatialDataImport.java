package com.ui.fragments.panelmenu.panelmenutabs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.pages.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Arrays;

import static com.ui.config.ConfigurationManager.config;

public class SpatialDataImport extends BasePage {

    private final Locator importInput = page.locator("id=points"); // открыть пространнственных данных
    private final Locator viewArea = page.locator("//div[contains(@class, 'ViewArea_viewArea')]");
    private final Locator readyToImportFiles = page.locator("//div[contains(@class, 'FileItem_tag')]");


    public SpatialDataImport(Page page) {
        super(page);
    }

    @Step("Импорт пространственных данных {filesNames}")
    public SpatialDataImport importSpatialData(String basePath, int sleep, String... filesNames) {

        waitLoading(1);
        //подготовка строки с путями к необходимым для загрузки файлам
        String[] fullPaths = Arrays.stream(filesNames)
                .map(name -> basePath + name) // Склеиваем basePath + имя файла
                .toArray(String[]::new);

        int filesAmount = filesNames.length;

        sendFiles(fullPaths, importInput);

        while (filesAmount != readyToImportFiles.count()) {
            waitLoading(1);
        }
        viewArea.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(50_000));

        waitLoading(sleep);
        return this;
    }

    @Step("Клик на нужный файл в каталоге данных")
    public SpatialDataImport clickOnFileInCatalog(String fileName){
        Locator file = page.locator("//div[contains(@class, 'DataDirectory_list')]//div[contains(@class, 'FileItem_name') and @title='"+fileName+"']");
        file.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        file.click();
        return this;
    }

    @Step("Нажатие на кнопку - Импортировать")
    public SpatialDataImport pressImportButton() {

        page.setDefaultTimeout(60_000);

//        page.locator("//span[text()='Импортировать']").click();
//        waitLoading(2);
        page.waitForResponse(
                response -> response.url().contains("/ggis/api/style/objects-styles/get-by-mo-uuids-with-template-legends") && response.status() == 200,
                () -> page.locator("//span[text()='Импортировать']").click()
        );


        page.locator("//span[text()='Каталог данных']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(50_000));
        page.setDefaultTimeout(config().timeOut());
        return this;
    }
}
