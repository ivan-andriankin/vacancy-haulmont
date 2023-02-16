package com.example.vacancyhaulmont;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.open;

public class SiteObjects {

    private SelenideElement element
    ;

    private ElementsCollection elements
    ;


    public SiteObjects openPage() {
        open("/");
        return this;
    }

}
