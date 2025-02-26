package com.ui.tests;

import com.ui.settings.ProjectSettings;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.File;

public class MyFirstTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {

        Thread.sleep(2000);

        mainPage.scene
                .getLeftToolMenu()
                .createPoint();

        mainPage.navigation.mouseClickByOffsetFromViewport(100, 100, "canvas");

        mainPage.navigation.pressKeyboardButton("Space");

        mainPage.navigation.clickRightButMouse(0,0, "canvas");

        mainPage.scene
                .getContextMenu()
                .clickOnContextMenuOption("Открыть в виде таблицы");

        mainPage.scene
                .getTableView()
                .exportDataFromTable();

        Thread.sleep(2000);

        File tableOfElements = mainPage.scene
                .getTableView()
                .getLatestDownloadedFile(ProjectSettings.DOWNLOAD_PATH);

        System.out.println(tableOfElements.getName());
    }

}
