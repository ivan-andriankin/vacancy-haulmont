package com.example.vacancyhaulmont;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
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
        siteObjects.openPage();
        $(".header__menu.header__menu_desctop").$$(".header__item").shouldHave(texts(buttons));
    }


    @DisplayName("Проверить фильтр проектов")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkProjectFilter() {
        siteObjects.openPage();
        $(".button.cookies__button").click();
        $(withTagAndText("span", "Все наши проекты")).click();
        assertEquals(Configuration.baseUrl + "/projects/", url());

        $$(".vue-treeselect.vue-treeselect--single.vue-treeselect--has-value.vue-treeselect--open-below")
                .findBy(text("Все отрасли")).click();
        $$(".label-tree").findBy(text("Разработка систем автоматизации")).click();
        $$(".vue-treeselect.vue-treeselect--single.vue-treeselect--has-value.vue-treeselect--open-below")
                .findBy(text("Все форматы работы")).click();
        $$(".label-tree").findBy(text("На базе платформы Jmix")).click();

        $$("div[class=solutions__item]" + ":not(.solutions-case-enter-to)")
                .findBy(text("Разработка систем автоматизации")).shouldBe(visible);
        $$("div[class=solutions__item]" + ":not(.solutions-case-enter-to)")
                .findBy(text("На базе платформы Jmix")).shouldBe(visible);
    }


    @DisplayName("Проверить колличество пунктов-загаловков в политике кофиденциальности")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkNumberOfHeaderItemsInPrivacyPolicy() {
        siteObjects.openPage();
        $(".button.cookies__button").click();
        $(withTagAndText("a", "Политика конфиденциальности")).click();
        assertEquals(Configuration.baseUrl + "/privacy-policy/", url());
        $$("h2[class=sub-title]").shouldHave(size(8));
    }


    @DisplayName("Проверка секции \"Услуги по разработке информационных систем\"")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkMainServicesSection() {
        siteObjects.openPage();
        $(".section.main-services").$(".head__title.title").shouldBe(visible);
        $(".section.main-services").$(".main-list.list-reset").$$("li").shouldHave(size(6));
        $(".section.main-services").$(".main-list__button").$(withTagAndText("span", "Все наши услуги"));
    }


    @DisplayName("Проверка правильности телефонного номера в футере")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void checkCorrectnesOfContactPhoneNumberInFooter() {
        siteObjects.openPage();
        $("a[class=footer__phone]").shouldHave(text("8 800 77 55 205"));
    }


}
