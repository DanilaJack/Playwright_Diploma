package com.ui.pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage{

    private final String usernameField = "//input[@placeholder='Логин']";
    private final String passwordField = "//input[@placeholder='Пароль']";
    private final String submitBtn = "enter-button";

    public LoginPage(Page page) {
        super(page);
    }

    public LoginPage typeUsername(String username) {
        page.fill(usernameField, username);
        return this;
    }

    public LoginPage typePassword(String password) {
        page.fill(passwordField, password);
        return this;
    }

    public LoginPage clickSubmitBtn(){
        page.getByTestId(submitBtn).click();
        return this;
    }
}
