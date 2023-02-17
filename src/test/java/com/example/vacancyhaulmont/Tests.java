package com.example.vacancyhaulmont;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("vacancy_tests")
public class Tests extends TestBase {


    @DisplayName("Верхнее меню")
    @Severity(SeverityLevel.NORMAL)
    @MethodSource("headerMenuItems")
    @ParameterizedTest(name="Проверить все кнопки меню {0} в хэдере")
    void checkAllButtonsInHeaderMenu(List<String> buttons) {
        siteObjects.openPage()
                .verifyAllButtonsInHeaderMenu(buttons);
    }


    @DisplayName("Проверить фильтр проектов")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkProjectFilter() {
        siteObjects.openPage()
                        .acceptCookies()
                        .goToProjectsPage()
                        .verifyPageUrl(projectsPageUrl)
                        .clickOnDropDownMenuInProjectsPage("Все отрасли")
                        .chooseDropDownOptionInProjectsPage("Разработка систем автоматизации")
                        .clickOnDropDownMenuInProjectsPage("Все форматы работы")
                        .chooseDropDownOptionInProjectsPage("На базе платформы Jmix")
                        .checkFilteredItem("Разработка систем автоматизации")
                        .checkFilteredItem("На базе платформы Jmix")
        ;
    }


    @DisplayName("Проверить колличество пунктов-загаловков в политике кофиденциальности")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkNumberOfHeaderItemsInPrivacyPolicy() {
        siteObjects.openPage()
                        .acceptCookies()
                        .goToPrivacyPolicyPage()
                        .verifyPageUrl(privacyPolicyPageUrl)
                        .countPrivacyPolicyTitles()
        ;
    }


    @DisplayName("Проверка секции \"Услуги по разработке информационных систем\"")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkMainServicesSection() {
        siteObjects.openPage()
                        .checkMainServicesTitle()
                        .countMainServicesElements()
                        .checkMainServicesButton()
        ;
    }


    @DisplayName("Проверка правильности телефонного номера в футере")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkCorrectnesOfContactPhoneNumberInFooter() {
        siteObjects.openPage()
                        .acceptCookies()
                        .checkContactPhoneNumber()
        ;
    }


}
