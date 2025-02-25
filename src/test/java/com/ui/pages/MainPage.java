package com.ui.pages;

import com.microsoft.playwright.Page;
import com.ui.helperUI.NavigationHelper;


public class MainPage extends BasePage{

    public final LoginPage loginPage;
    public final SessionPage sessionPage;
    public final ScenePage scene;
    public final NavigationHelper navigation;


    public MainPage(Page page) {
        super(page);
        loginPage = new LoginPage(page);
        sessionPage = new SessionPage(page);
        scene = new ScenePage(page);
        navigation = new NavigationHelper(page);
    }

}
