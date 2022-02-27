package com.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class AccountPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //локатор поля Имя (disabled)
    @FindBy(how = How.XPATH,using = "//input[@name='Name']")
    private SelenideElement nameField;

    //локатор кнопки Выход
    @FindBy(how = How.XPATH,using = "//li[@class='Account_listItem__35dAP']/button[text()='Выход']")
    private SelenideElement exitButton;

    @Step("Проверить открыта ли страница личного кабинета")
    public AccountPage checkIsAccountPageOpen(String value) {
        webdriver().shouldHave(url(AccountPage.URL));
        nameField.shouldHave(Condition.exactValue(value));
        return this;
    }

    @Step("Клик по кнопке Выход")
    public LoginPage clickExitButton() {
        exitButton.scrollIntoView(true).click();
        return page(LoginPage.class);
    }

}
