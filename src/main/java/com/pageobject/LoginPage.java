package com.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //локатор поля ввода Email
    @FindBy(how = How.XPATH,using = "//label[text()='Email']/../input")
    private SelenideElement emailField;

    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH,using = "//label[text()='Пароль']/../input")
    private SelenideElement passwordField;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH,using = "//form[@class='Auth_form__3qKeq mb-20']/button[text()='Войти']")
    private SelenideElement loginButton;

    //локатор ссылки Зарегистрироваться
    @FindBy(how = How.CLASS_NAME,using = "Auth_link__1fOlj")
    private SelenideElement registerLink;

    @Step("Заполнить email, пароль и нажать на кнопку Войти")
    public MainPage login(String email, String password) {
        sleep(2000);
        emailField.scrollIntoView(true).setValue(email);
        passwordField.setValue(password);
        loginButton.click();
        return page(MainPage.class);
    }

    @Step("Клик по ссылке Зарегистрироваться")
    public RegistrationPage clickRegisterLink() {
        registerLink.scrollIntoView(true).click();
        return page(RegistrationPage.class);
    }

    @Step("Проверить осуществился ли выход")
    public LoginPage checkIsUserLogout() {
        webdriver().shouldHave(url(LoginPage.URL));
        emailField.shouldBe(Condition.visible);
        return this;
    }
}
