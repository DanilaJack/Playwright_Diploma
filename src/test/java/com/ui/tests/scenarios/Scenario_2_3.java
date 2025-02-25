package com.ui.tests.scenarios;

import com.ui.settings.ProjectSettings;
import com.ui.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Scenario_2_3 extends BaseTest {

    @Test
    public void scenarioTest_1_approach() throws InterruptedException, IOException {

        String[] resources = {"ВКРАП_1ПЭ_марк_точки.DAT", "ВКРАП_1ПЭ_марк_точки_новые.DAT", "ПЛАН_ВКРАП_1ПЭ.STR"};

        mainPage.scene
                .getPanelMenu()
                .pressFileBut()
                .getSpatialDataImport()
                .importSpatialData(ProjectSettings.RESOURCES_PATH_FILES_FOR_SCENARIOS, 3, resources[0], resources[1], resources[2])
                .clickOnFileInCatalog(resources[0])
                .pressImportButton();

        mainPage.scene
                .getRightmenu()
                .pressObjectsOnStageButton()
                .getObjectsOnScene()
                .unrollAllFoldersInObjects();

        for (String s: resources){
            Assert.assertTrue(
                    mainPage.scene
                            .getRightmenu()
                            .getObjectsOnScene()
                            .isThereMOInSceneObjects(s)
            );
        }

        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[1])
                .clickFocusBut();

        mainPage.scene
                .getContextPanel()
                .enterEditModeOfMO();

        mainPage.navigation
                .clickRightButMouse(100,100,"canvas");

        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Открыть в виде таблицы");

        mainPage.scene
                .getTableView()
                .clickOnNeededTabOfMultiobject("Геометрия")
                .highlightColumn("Имя ГО")
                .clickOnBackToSceneModeBut();

        mainPage.navigation.clampingAndPressKeyboardButtons("Control", "C");
        mainPage.navigation.pressKeyboardButton("Delete");

        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[0]);

        mainPage.scene
                .getContextPanel()
                .enterEditModeOfMO();

        mainPage.navigation.clickRightButMouse(0,0,"canvas");

        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Вставить в исходные координаты");

        mainPage.navigation.pressKeyboardButton("Escape");

        mainPage.scene
                .getRightmenu()
                .pressPropertiesButton();

        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[0]);

        mainPage.scene
                .getRightmenu()
                .getProperties()
                .changeAttributeValue("Имя", "МаркТочки")
                .openBind()
                .chooseSpace("Пр-во_Башлыков_НеТрогать")
                .chooseTemplate("Точки_съемки_поверхностей_складов_2_8")
                .pressCreateBut();

        resources[0] = "МаркТочки";

        mainPage.scene
                .getRightmenu()
                .getObjectsOnScene()
                .selectObject(resources[2]);

        mainPage.scene
                .getRightmenu()
                .getProperties()
                .openBind()
                .chooseSpace("Пр-во_Башлыков_НеТрогать")
                .chooseTemplate("Гео карта")
                .pressCreateBut();

        mainPage.scene
                .getRightmenu()
                .pressObjectsOnStageButton()
                .pressObjectsInStorageBtn()
                .getObjectsInStorage()
                .pressSpaceButton()
                .connectSpace("Пр-во_Башлыков_НеТрогать")
                .setSpace("Пр-во_Башлыков_НеТрогать")
                .unrollAllFoldersInStorage();

        Assert.assertTrue(mainPage.scene
                .getRightmenu()
                .getObjectsInStorage()
                .isThereAnObjectInStorage(resources[0]));

        Assert.assertTrue(mainPage.scene
                .getRightmenu()
                .getObjectsInStorage()
                .isThereAnObjectInStorage(resources[2]));

        mainPage.scene
                .getRightmenu()
                .getObjectsInStorage()
                .pressNetBut()
                .pressEyeButton("ВОСТОК_Ц_О_ЮГ_ОСИ_Л.csv");

        mainPage.scene
                .getViewPanel()
                .turnOnAutoGrid();

        mainPage.navigation.mouseClickByOffsetFromViewport(-300, -300, "canvas");

        Thread.sleep(2000);

        //imageComparison
        mainPage.imageComparison(ProjectSettings.RESOURCES_PATH_EXP_SCREENSHOTS, "scenario_2_3_exp.png", ProjectSettings.RESOURCES_PATH_CUR_SCREENSHOTS, "scenario_2_3_cur.png");

        mainPage.scene
                .getRightmenu()
                .pressObjectsInStorageBtn()
                .pressObjectsOnStageButton()
                .getObjectsOnScene()
                .unrollAllFoldersInObjects()
                .selectObject(resources[0])
                .clickFocusBut();

        mainPage.scene
                .getContextPanel()
                .enterEditModeOfMO();

        mainPage.navigation.clickRightButMouse(100,100,"canvas");

        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Открыть в виде таблицы");

        int rowsNum = mainPage.scene
                .getTableView()
                .clickOnNeededTabOfMultiobject("Элементы")
                .getFullAmountOfElements();

        mainPage.scene
                .getTableView()
                .exportDataFromTable();

        Thread.sleep(5000);

    }
}
