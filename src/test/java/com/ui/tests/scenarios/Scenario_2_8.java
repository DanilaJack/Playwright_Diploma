package com.ui.tests.scenarios;

import com.ui.settings.ProjectSettings;
import com.ui.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.ui.config.ConfigurationManager.config;
import static com.utils.TxtFileProcessing.fileReaderIntoDoubleArray;
import static java.lang.Thread.sleep;

public class Scenario_2_8 extends BaseTest {

    @Test
    public void scenarioTest_1_approach() throws InterruptedException, IOException {
        page = browserContext.newPage();
        page.navigate(config().baseUrl());
//        //Объекты для импорта
//        String[] resources = {"Поверхность основания склада", "Точки съемки склада породы"};
//
//        mainPage.navigation.mouseClickByOffsetFromViewport(100, 100, "canvas");
//        mainPage.navigation.zoomCanvas(-100);
//
//        mainPage.scene
//                .getPanelMenu()
//                .pressFileBut()
//                .getSpatialDataImport()
//                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, 5, "/scenario_2_8/"+resources[0]+".tridb", "/scenario_2_8/"+resources[1]+".DAT")
//                .clickOnFileInCatalog(resources[0]+".tridb")
//                .pressImportButton();
//
//
//        mainPage.scene
//                .getRightmenu()
//                .pressObjectsOnStageButton()
//                .getObjectsOnScene()
//                .unrollAllFoldersInObjects();
//
//        // Проверка, что импортированная поверхность основания склада пришла в объекты сессии
//        Assert.assertTrue(mainPage.scene
//                .getRightmenu()
//                .getObjectsOnScene()
//                .isThereMOInSceneObjects(resources[0]));
//
//        // Проверка, что точки съемки склада породы пришли в объекты сессии
//        Assert.assertTrue(mainPage.scene
//                .getRightmenu()
//                .getObjectsOnScene()
//                .isThereMOInSceneObjects(resources[1]));
//
//        // Выделить поверхность основания склада в дереве объектов сессии
//        mainPage.scene
//                .getRightmenu()
//                .getObjectsOnScene()
//                .selectObject(resources[0]);
//
//        // Привязать выделенный объект к ШБО
//        mainPage.scene
//                .getRightmenu()
//                .pressPropertiesButton()
//                .getProperties()
//                .openBind();
//
//        mainPage.scene
//                .getRightmenu()
//                .getBindBusinessObjects()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Поверхности_складов")
//                .pressCreateBut();
//
//        mainPage.scene
//                .getRightmenu()
//                .getObjectsOnScene()
//                .selectObject(resources[1]);
//
//        // Привязать выделенный объект к ШБО
//        mainPage.scene
//                .getRightmenu()
//                .getProperties()
//                .openBind();
//
//        mainPage.scene
//                .getRightmenu()
//                .getBindBusinessObjects()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Точки_съемки_поверхностей_складов")
//                .pressCreateBut();
//
//        // Выбрать пространство в объектах хранилища
//        mainPage.scene
//                .getRightmenu()
//                .pressObjectsOnStageButton()
//                .pressPropertiesButton()
//                .pressObjectsInStorageBtn()
//                .getObjectsInStorage()
//                .pressSpaceButton()
//                .connectSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .setSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .unrollAllFoldersInStorage();
//
//        // Проверка что объекты записались в рабочее пространство
//        Assert.assertTrue(mainPage.scene
//                .getRightmenu()
//                .getObjectsInStorage()
//                .isThereAnObjectInStorage(resources[0]));
//
//        Assert.assertTrue(mainPage.scene
//                .getRightmenu()
//                .getObjectsInStorage()
//                .isThereAnObjectInStorage(resources[1]));
//
//        mainPage.scene
//                .getRightmenu()
//                .pressObjectsInStorageBtn()
//                .pressObjectsOnStageButton()
//                .getObjectsOnScene()
//                .unrollAllFoldersInObjects()
//                .hoverAndChangeVisibility(resources[0]+", "+resources[0]);
//
//        mainPage.scene
//                        .getRightmenu()
//                                .getObjectsOnScene()
//                                        .selectObject(resources[1]);
//
//        mainPage.scene
//                .getLeftToolMenu()
//                .createDigitalSurface();
//
//        mainPage.navigation.pressKeyboardButton("Escape");
//
//        mainPage.scene
//                .getRightmenu()
//                .pressPropertiesButton()
//                .getProperties()
//                .openBind();
//
//        mainPage.scene
//                .getRightmenu()
//                .getBindBusinessObjects()
//                .chooseSpace("Пр-во_Башлыков_НеТрогать_2_8")
//                .chooseTemplate("Каркасы_складов")
//                .pressCreateBut();
//
//        mainPage.scene
//                .getRightmenu()
//                .pressPropertiesButton()
//                .getObjectsOnScene()
//                .unrollFolderForName("Новые полигональные модели 1")
//                .hoverAndChangeVisibility(resources[0]+", "+resources[0])
//                .hoverAndChangeVisibility(resources[1]+".DAT");
//
//        mainPage.scene
//                .getViewPanel()
//                .settingCameraInPlaneTopAndBottom("Вид сверху");
//
//        mainPage.navigation.clampingKeyboardButtons("Control");
//
//        mainPage.scene
//                .getRightmenu()
//                .getObjectsOnScene()
//                .selectObject(resources[0])
//                .selectObject("Полигональные модели 1");
//
//        mainPage.navigation.releaseKeyboardButtons("Control");
//
//        mainPage.scene
//                .getContextPanel()
//                .pressVolumeBetweenSurfaces();
//
//        mainPage.navigation.pressKeyboardButton("Escape");
//
//        mainPage.scene
//                .getRightmenu()
//                .pressPropertiesButton()
//                .getProperties()
//                .switchToOtherTabForFilling()
//                .clickAddNewGroupOfPropsForPM()
//                .giveNameToGroupOfProps("Группа свойств склада")
//                .addNewPropToGroupOfProps("Группа свойств склада","Число", "Плотность", "2.85");
//
//        mainPage.navigation.pressKeyboardButton("Escape");
//
//        mainPage.scene
//                .getPanelMenu()
//                .pressMoveToCanvas();
//
//        mainPage.scene
//                .getLeftToolMenu()
//                .addNewListToCanvas("Произвольный");
//
//        mainPage.navigation.mouseClickByOffsetFromViewport(300,200,"canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(300,700,"canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(1000,700,"canvas");
//
//        mainPage.scene
//                .getLeftToolMenu()
//                .addNewFragmentToCanvas();
//
//        mainPage.navigation.mouseClickByOffsetFromViewport(400, 300, "canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(400, 600, "canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(800, 600, "canvas");
//
//        // Вернуться в представление 3d сцены
//        mainPage.scene
//                .getPanelMenu()
//                .pressMoveToScene();
//
//        // Выбрать инструмент проектирования разреза
//        mainPage.scene
//                .getLeftToolMenu()
//                .createCut();
//
//        // Спроектировать разрез
//        mainPage.navigation.mouseClickByOffsetFromViewport(-150, 150, "canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(200, -200, "canvas");
//        mainPage.navigation.pressKeyboardButton("Space");
//
//        // Перейти в вид с ограничениями
//        mainPage.scene
//                .getContextPanel()
//                .moveToCutView();
//
//        // Установить вид 2d срез
//        mainPage.scene
//                .getViewPanel()
//                .set2D_Slice();
//
//        // Вновь перейти в представление Холста
//        mainPage.scene
//                .getPanelMenu()
//                .pressMoveToCanvas();
//
//        // Выбрать инструмент проектирования фрагмента
//        mainPage.scene
//                .getLeftToolMenu()
//                .addNewFragmentToCanvas();
//
//        // Спроектировать фрагмент
//        mainPage.navigation.mouseClickByOffsetFromViewport(-250, 0, "canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(-250, 100, "canvas");
//        mainPage.navigation.mouseClickByOffsetFromViewport(100, 100, "canvas");
//
//        // Открыть стили предварительно для заполнения полей таблицы
//        mainPage.scene
//                .getRightmenu()
//                .pressStylesButton();
//
//        int x = 40, y = 24, k = 14;
//        for (int i = 0; i < 2; i++){
//
//            // Выбрать инструмент проектирования прямоугольника на Холсте
//            mainPage.scene
//                    .getLeftToolMenu()
//                    .createRectangle();
//
//            // Задать стартовую точку
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setAxisX(String.valueOf(x))
//                    .setAxisY(String.valueOf(y));
//
//            mainPage.navigation.pressKeyboardButton("Enter");
//
//            // Задать угол и длину первой стороны
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setAngle("270")
//                    .setLength("3");
//
//            mainPage.navigation.pressKeyboardButton("Enter");
//
//            // Задать длину второй стороны
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setLength("14");
//
//            mainPage.navigation.pressKeyboardButton("Enter");
//            mainPage.navigation.pressKeyboardButton("Escape");
//            mainPage.navigation.pressKeyboardButton("Space");
//
//            // Добавляю метку в прямоугольник
//            mainPage.scene
//                    .getRightmenu()
//                    .getStyles()
//                    .setMarkText("Метка")
//                    .changeMarkVisibility();
//
//            // Логика на смену координат
//            if (i % 2 != 0){
//                y -= 3;
//            }
//            x -= k;
//            k = -k;
//        }
//
//        List<List<String>> data = fileReaderIntoDoubleArray(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, "/scenario_2_8/Coordinates_scenario_2_8.txt");
//
//        for (List<String> row : data) {
//            mainPage.scene
//                    .getLeftToolMenu()
//                    .createRectangle();
//
//            // Задать стартовую точку
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setAxisX(row.get(0))
//                    .setAxisY(row.get(1));
//            mainPage.navigation.pressKeyboardButton("Enter");
//
//            // Задать угол и длину первой стороны
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setAngle(row.get(2))
//                    .setLength(row.get(3));
//            mainPage.navigation.pressKeyboardButton("Enter");
//
//            // Задать длину второй стороны
//            mainPage.scene
//                    .getParameterizedDesign()
//                    .setLength(row.get(4));
//
//            mainPage.navigation.pressKeyboardButton("Enter");
//            mainPage.navigation.pressKeyboardButton("Escape");
//            mainPage.navigation.pressKeyboardButton("Space");
//
//            // Добавляю метку в прямоугольник
//            mainPage.scene
//                    .getRightmenu()
//                    .getStyles()
//                    .setMarkText(row.get(5))
//                    .changeMarkVisibility();
//        }
//
//        // Закрыть панель стилей в правом меню
//        mainPage.scene
//                .getRightmenu()
//                .pressStylesButton();
//
//        mainPage.navigation.mouseClickByOffsetFromViewport(-200, -200, "canvas");
//
//        // Получить текущее время
//        String currentDateTime1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm"));
//
//        // Далее идет экспорт холста в dxf
//        mainPage.scene
//                .getPanelMenu()
//                .pressFileBut()
//                .pressExportFromCanvas();
//
//        // 2 секунды на размышление
//        sleep(2000);
//
//        // Получить последний экспортируемый файл
//        File exportedFile = mainPage.scene
//                .getPanelMenu()
//                .getLatestDownloadedFile(ProjectSettings.DOWNLOAD_PATH);
//
//        // Получить текущее время
//        String currentDateTime2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm"));
//
//        // Проверка, что файл пришел в хранилище с импользованием проверку на время
//        Assert.assertTrue(exportedFile.getName().contains(currentDateTime2) || exportedFile.getName().contains(currentDateTime1));
    }
}
