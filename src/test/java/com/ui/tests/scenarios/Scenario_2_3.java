package com.ui.tests.scenarios;

import com.ui.settings.ProjectSettings;
import com.ui.tests.BaseTest;
import com.utils.Excel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class Scenario_2_3 extends BaseTest {

    @Test
    public void scenario_2_3() throws InterruptedException {

        //todo: Костылямба
        mainPage.navigation.mouseClickByOffsetFromViewport(100, 0, "canvas");
        mainPage.navigation.zoomCanvas(-100);

        // Объекты для импорта
        String[] resources = {"ВКРАП_1ПЭ_марк_точки.DAT", "ВКРАП_1ПЭ_марк_точки_новые.DAT", "ПЛАН_ВКРАП_1ПЭ.STR"};
        //Импортировать объекты из файловой системы
        mainPage.scene
                .getPanelMenu()
                .pressFileBut()
                .getSpatialDataImport()
                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, 5,"/scenario_2_3/"+resources[0], "/scenario_2_3/"+resources[2])
                .clickOnFileInCatalog(resources[0])
                .pressImportButton();

        // Раскрыть все папки в объектах сессии
        mainPage.scene
                .getRightmenu()
                .pressObjectsOnStageButton()
                .getObjectsOnScene()
                .unrollAllFoldersInObjects();

        Assert.assertTrue(
                mainPage.scene
                        .getRightmenu()
                        .getObjectsOnScene()
                        .isThereMOInSceneObjects(resources[0]));

        Assert.assertTrue(
                mainPage.scene
                        .getRightmenu()
                        .getObjectsOnScene()
                        .isThereMOInSceneObjects(resources[2]));

        // Нажать на иконку Свойств в правом меню
        mainPage.scene
                .getRightmenu()
                .pressPropertiesButton();

        // Выбрать объект маркшейдерских точек в объектах сессии
        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[0]);

        // Привязать этот объект к шаблону БО
        mainPage.scene
                .getRightmenu()
                .getProperties()
                .changeAttributeValue("Имя", "МаркТочки")
                .openBind();

        mainPage.scene
                .getRightmenu()
                .getBindBusinessObjects()
                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .chooseTemplate("МаркшейдерскиеТочки")
                .pressCreateBut();

        //Выбрать объект географической карты в объектах сессии
        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[2]);

        // Привязать его к шаблону БО
        mainPage.scene
                .getRightmenu()
                .getProperties()
                .openBind();

        mainPage.scene
                .getRightmenu()
                .getBindBusinessObjects()
                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .chooseTemplate("Гео карта")
                .pressCreateBut();

        // Раскрыть все объекты в соответствующем пространстве в объектах в хранилище
        mainPage.scene
                .getRightmenu()
                .pressPropertiesButton()
                .pressObjectsOnStageButton()
                .pressObjectsInStorageBtn()
                .getObjectsInStorage()
                .pressSpaceButton()
                .connectSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .setSpace("Пр-во_Башлыков_НеТрогать_2_3")
                .unrollAllFoldersInStorage();

        resources[0] = "МаркТочки";

        // Проверка на существование объектов в пространстве
        Assert.assertTrue(mainPage.scene
                .getRightmenu()
                .getObjectsInStorage()
                .isThereAnObjectInStorage(resources[0]));

        Assert.assertTrue(mainPage.scene
                .getRightmenu()
                .getObjectsInStorage()
                .isThereAnObjectInStorage(resources[2]));

        mainPage.scene
                .getPanelMenu()
                .pressFileBut()
                .getSpatialDataImport()
                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, 2,"/scenario_2_3/"+resources[1])
                .selectAddToObject()
                .selectInputObject(resources[0])
                .pressImportButton();

        Thread.sleep(2000);

// Сфокусироваться на новых точках
        mainPage.scene
                .getRightmenu()
                .pressObjectsOnStageButton()
                .getObjectsOnScene()
                .unrollAllFoldersInObjects()
                .selectObject(resources[0])
                .clickFocusBut();

        Assert.assertEquals(mainPage.scene
                .getRightmenu()
                .pressPropertiesButton()
                .getProperties()
                .getAamountOfGeoObjects(), 75);

        mainPage.scene
                .getRightmenu()
                .ClickPropertiesMenu();

        mainPage.scene
                .getViewPanel()
                .zoomOut();

        // Войти в режим редактирования МО новые точки
        mainPage.scene
                .getContextPanel()
                .enterEditModeOfMO();

        // Кликнуть правой кнопкой мыши для вызова контекстного меню
        mainPage.navigation
                .clickRightButMouse(300, 300, "canvas");

        // Открыть сцену 3d в виде таблицы
        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Открыть в виде таблицы");

        // В табличном представление перейти на вкладку 'Геометрия' и выделить все ГО и вернуться обратно на сцену
        mainPage.scene
                .getTableView()
                .clickOnNeededTabOfMultiobject("Геометрия")
                .globalSearch("18330")
                .editCellInTable("18330", "Имя ГО", "Участок 10")
                .clickOnNeededTabOfMultiobject("Элементы")
                .globalSearch("18350")
                .editCellInTable("18350", "Y", "29590")
                .clickOnBackToSceneModeBut();

        mainPage.scene
                .getPanelMenu()
                .pressSaveSettings();

        mainPage.navigation.clickRightButMouse(300, 300, "canvas");

        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Открыть в виде таблицы");

        int rowsNum = mainPage.scene
                .getTableView()
                .clickOnNeededTabOfMultiobject("Элементы")
                .getFullAmountOfElements2();

        mainPage.scene
                .getTableView()
                .exportDataFromTable();


        Thread.sleep(5000);

//        File tableOfElements = mainPage.scene
//                .getTableView()
//                .getLatestDownloadedFile(ProjectSettings.DOWNLOAD_PATH);
//
//        int rowsNum2 = Excel.getRowCount(tableOfElements);
//
//        Assert.assertEquals(rowsNum, rowsNum2);

    }
}
