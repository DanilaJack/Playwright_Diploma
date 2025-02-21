package com.ui.tests;

import org.testng.annotations.Test;

public class MyFirstTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {

        mainPage.scene
                .getRightmenu()
                .pressObjectsInStorageBtn();

    }

}
