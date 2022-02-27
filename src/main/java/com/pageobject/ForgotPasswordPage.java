package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //локатор поля ввода Email
    @FindBy(how = How.XPATH,using = ".//label[text()='Email']/../input")
    private SelenideElement emailField;

    //локатор кнопки Восстановить
    @FindBy(how = How.XPATH,using = ".//button[text()='Восстановить']")
    private SelenideElement restoreButton;

    //локатор ссылки Войти
    @FindBy(how = How.CLASS_NAME,using = "Auth_link__1fOlj")
    private SelenideElement loginLink;

    @Step("Клик по ссылке  Войти")
    public LoginPage clickLoginLink() {
        loginLink.scrollIntoView(true).click();
        return page(LoginPage.class);
    }
}
