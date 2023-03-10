package com.example.vacancyhaulmont;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.vacancyhaulmont.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.stream.Stream;

public class TestBase {

    SiteObjects siteObjects = new SiteObjects();

    static String projectsPageUrl = "/projects/";
    static String privacyPolicyPageUrl = "/privacy-policy/";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.haulmont.ru";
        Configuration.browser = System.getProperty("browserName","chrome");
        Configuration.browserVersion = System.getProperty("browserVersion","100.0");
        Configuration.browserSize = System.getProperty("screenSize","1366x768");
        Configuration.remote = System.getProperty("remoteBrowserAddress");  // передается из дженкинса "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);    // окошко в окошке
        capabilities.setCapability("enableVideo", true);  // запись видео
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    static Stream<Arguments> headerMenuItems() {
        return Stream.of(
            Arguments.of(List.of(
                "Продукты",
                "Заказная разработка",
                "Отрасли",
                "Проекты",
                "Импортозамещение",
                "О компании",
                "Блог"))
            );
    }

}
