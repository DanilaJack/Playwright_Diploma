package com.ui.config;

import org.aeonbits.owner.Config;

// Благодаря Policy Merge я смогу переопределить некоторые конфиги например из config.properties
// поэтому и прописано system.properties
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:config.properties", "classpath:allure.properties"})
public interface Configuration extends Config {

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("base.url")
    String baseUrl();

    @Key("base.test.video.path")
    String baseTestVideoPath();

    @Key("browser")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("slow.motion")
    int slowMotion();

    @Key("timeout")
    int timeOut();

    @Key("video")
    boolean video();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("file_detector_included")
    boolean fileDetectorIncluded();

    // Но можно поставить key только где ключи составные, типа base.url, остальные поймет и так
}
