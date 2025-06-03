package com.ui.tests;

import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.ui.browsersettings.BrowserManager;
import com.ui.pages.MainPage;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static com.ui.config.ConfigurationManager.config;
import static com.ui.helperAPI.Authorization.getAccessToken;
import static com.ui.helperAPI.Sessions.delete_all_sessions;

public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected MainPage mainPage;
    private boolean needVideo;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", config().browser().toUpperCase())
                        .put("Context URL", config().baseUrl()).build(),
                config().allureResultsDir()+"/");

        if (config().video()){
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get(config().baseTestVideoPath()))
                    .setViewportSize(null)
            );
        }
        else {
            browserContext = browser.newContext();
        }
        browserContext.setDefaultTimeout(config().timeOut());

        String accessToken = getAccessToken(config().username(), config().password());
        delete_all_sessions();

//        List<Cookie> cookies = List.of(
//                new Cookie("access-token", accessToken)
//                        .setDomain("app.stage.ggis.iccdev.ru")    // Устанавливаем домен
//                        .setPath("/")                // Устанавливаем путь
//                        .setHttpOnly(false)           // Устанавливаем HttpOnly
//                        .setSecure(false)         // Устанавливаем Secure
//        );
//        browserContext.addCookies(cookies);


        page = browserContext.newPage();
        page.navigate(config().baseUrl());
        mainPage = new MainPage(page);

        page.locator("#username").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        page.locator("#username").fill(config().username());

        Thread.sleep(1000);
        page.locator("#password").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3_000));
        page.locator("#password").fill(config().password());

        Thread.sleep(1000);
        page.locator("#kc-login").click();
        page.waitForTimeout(20_000);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            needVideo = true;
            captureScreenshotOnFailure();
        }
        browserContext.close();
        if(config().video() && needVideo){
            captureVideo();
        }
        needVideo = false;
    }


    @Attachment(value = "Test Video", type = "video/webm")
    @SneakyThrows
    private byte[] captureVideo(){
        return Files.readAllBytes(page.video().path());
    }

    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    private byte[] captureScreenshotOnFailure(){
        return page.screenshot();
    }
}
