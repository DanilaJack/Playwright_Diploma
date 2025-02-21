package com.ui.pages;

import com.microsoft.playwright.Page;


public class MainPage extends BasePage{

    public final LoginPage loginPage;
    public final SessionPage sessionPage;
    public final ScenePage scene;


    public MainPage(Page page) {
        super(page);
        loginPage = new LoginPage(page);
        sessionPage = new SessionPage(page);
        scene = new ScenePage(page);
    }

}
