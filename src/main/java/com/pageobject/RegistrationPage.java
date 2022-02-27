package com.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    //локатор поля ввода Имя
    @FindBy(how = How.XPATH,using = "//label[text()='Имя']/../input")
    private SelenideElement nameInputField;

    //локатор поля ввода Email
    @FindBy(how = How.XPATH,using = "//label[text()='Email']/../input")
    private SelenideElement emailInputField;

    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH,using = "//label[text()='Пароль']/../input")
    private SelenideElement passwordInputField;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH,using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //локатор сообщения о некорректном пароле
    @FindBy(how = How.XPATH,using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordMessage;

    //локатор ссылки Войти
    @FindBy(how = How.CLASS_NAME,using = "Auth_link__1fOlj")
    private SelenideElement loginLink;


    @Step("Ввести имя, email, пароль и нажать на кнопку Зарегистрироваться")
    public LoginPage register(String name, String email, String password) {
        nameInputField.setValue(name);
        emailInputField.setValue(email);
        passwordInputField.setValue(password);
        registerButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик по ссылке Войти")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return page(LoginPage.class);
    }

    @Step("Проверить наличие сообщения с текстом Некорректный пароль")
    public RegistrationPage isIncorrectPasswordMessageDisplayed() {
        incorrectPasswordMessage.shouldHave(Condition.exactText("Некорректный пароль"));
        return this;
    }
}