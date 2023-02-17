package com.example.vacancyhaulmont;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteObjects {


    private SelenideElement
            cookiesButton = $(".button.cookies__button"),
            projectsPageButton = $(withTagAndText("span", "Все наши проекты")),
            privacyPolicyButton = $(withTagAndText("a", "Политика конфиденциальности")),
            mainServicesTitle = $(".section.main-services").$(".head__title.title"),
            mainServicesButton = $(".section.main-services").$(".main-list__button").$(withTagAndText("span", "Все наши услуги")),
            contactPhoneNumber = $("a[class=footer__phone]")

    ;

    private ElementsCollection
            headerMenuItems = $(".header__menu.header__menu_desctop").$$(".header__item"),
            clickOnDropDownMenu = $$(".vue-treeselect.vue-treeselect--single.vue-treeselect--has-value.vue-treeselect--open-below"),
            dropDownOptionsInProjectsPage = $$(".label-tree"),
            filteredItem = $$("div[class=solutions__item]" + ":not(.solutions-case-enter-to)"),
            privacyPlicyTitles = $$("h2[class=sub-title]"),
            mainServicesElements = $(".section.main-services").$(".main-list.list-reset").$$("li")
    ;


    public SiteObjects openPage() {
        open("/");
        return this;
    }

    public SiteObjects verifyAllButtonsInHeaderMenu(List<String> value) {
        headerMenuItems.shouldHave(texts(value));
        return this;
    }

    public SiteObjects acceptCookies() {
        cookiesButton.click();
        return this;
    }

    public SiteObjects goToProjectsPage() {
        projectsPageButton.click();
        return this;
    }

    public SiteObjects verifyPageUrl(String value) {
        assertEquals(Configuration.baseUrl + value, url());
        return this;
    }

    public SiteObjects clickOnDropDownMenuInProjectsPage(String value) {
        clickOnDropDownMenu.findBy(text(value)).click();
        return this;
    }

    public SiteObjects chooseDropDownOptionInProjectsPage(String value) {
        dropDownOptionsInProjectsPage.findBy(text(value)).click();
        return this;
    }

    public SiteObjects checkFilteredItem(String value) {
        filteredItem.findBy(text(value)).shouldBe(visible);
        return this;
    }

    public SiteObjects goToPrivacyPolicyPage() {
        privacyPolicyButton.click();
        return this;
    }

    public SiteObjects countPrivacyPolicyTitles() {
        privacyPlicyTitles.shouldHave(size(8));
        return this;
    }

    public SiteObjects checkMainServicesTitle() {
        mainServicesTitle.shouldBe(visible);
        return this;
    }

    public SiteObjects countMainServicesElements() {
        mainServicesElements.shouldHave(size(6));
        return this;
    }

    public SiteObjects checkMainServicesButton() {
        mainServicesButton.shouldBe(visible);
        return this;
    }

    public SiteObjects checkContactPhoneNumber() {
        contactPhoneNumber.shouldHave(text("8 800 77 55 205"));
        return this;
    }



}
