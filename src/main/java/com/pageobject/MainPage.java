package com.pageobject;

import io.qameta.allure.Step;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH,using = "//p[text()='Конструктор']")
    private SelenideElement constructorLink;

    //локатор логотипа Stellar Burgers
    @FindBy(how = How.CLASS_NAME,using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgersLogo;

    //локатор кнопки Личный Кабинет
    @FindBy(how = How.XPATH,using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalAccountLink;

    //локатор заголовка Соберите бургер
    @FindBy(how = How.XPATH,using = "//h1[text()='Соберите бургер']")
    private SelenideElement makeBurgerHeader;

    //локатор кнопки Войти в аккаунт
    @FindBy(how = How.XPATH,using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    //локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH,using = "//button[text()='Оформить заказ']")
    private SelenideElement orderButton;

    //локатор вкладки Булки
    @FindBy(how = How.XPATH,using = "//span[text()='Булки']")
    private SelenideElement bunsTab;

    //локатор заголовка Булки
    @FindBy(how = How.XPATH,using = "//h2[text()='Булки']")
    private SelenideElement bunsHeader;

    //локатор вкладки Соусы
    @FindBy(how = How.XPATH,using = "//span[text()='Соусы']")
    private SelenideElement saucesTab;

    //локатор заголовка Соусы
    @FindBy(how = How.XPATH,using = "//h2[text()='Соусы']")
    private SelenideElement saucesHeader;

    //локатор вкладки Начинки
    @FindBy(how = How.XPATH,using = "//span[text()='Начинки']")
    private SelenideElement fillingTab;

    //локатор заголовка Начинки
    @FindBy(how = How.XPATH,using = "//h2[text()='Начинки']")
    private SelenideElement fillingsHeader;

    //локатор
    @FindBy(how = How.XPATH,using = "//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/ul")
    private List<SelenideElement> listIngredients;

    //Методы
    @Step("Клик по ссылке Конструктор")
    public MainPage clickConstructorLink() {
        constructorLink.click();
        return page(MainPage.class);
    }

    @Step("Клик по логотипу Stellar Burgers")
    public MainPage clickStellarBurgersLogo() {
        stellarBurgersLogo.click();
        return page(MainPage.class);
    }

    @Step("Клик по кнопке Личный Кабинет - переход в Личный кабинет")
    public AccountPage clickAccountButton() {
        personalAccountLink.click();
        return page(AccountPage.class);
    }

    @Step("Клик по кнопке Личный Кабинет - переход на страницу логина")
    public LoginPage clickAccountButtonLogin() {
        personalAccountLink.click();
        return page(LoginPage.class);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Залогиниться с главной страницы")
    public MainPage loginFromMainPage(String email, String password) {
        clickLoginButton().login(email, password);
        return page(MainPage.class);
    }

    @Step("Проверить что открыта главная страница")
    public MainPage checkIsMainPageOpen() {
        webdriver().shouldHave(url(MainPage.URL));
        makeBurgerHeader.shouldHave(Condition.exactText("Соберите бургер"));
        orderButton.shouldHave(Condition.visible);
        return this;
    }

    @Step("Клик по вкладке Булки")
    public MainPage clickBunsTab() {
        executeJavaScript("arguments[0].click();", bunsTab);
        return page(MainPage.class);
    }

    @Step("Клик по вкладке Соусы")
    public MainPage clickSaucesTab() {
        executeJavaScript("arguments[0].click();", saucesTab);
        return page(MainPage.class);
    }

    @Step("Клик по вкладке Начинки")
    public MainPage clickFillingsTab() {
        executeJavaScript("arguments[0].click();", fillingTab);
        return page(MainPage.class);
    }

    @Step("Проверить наличие блока со списком ингредиентов - Булки, Соусы, Начинки")
    public MainPage listOfIngredientsIsDisplayed() {
        listIngredients.get(0).shouldBe(Condition.visible);
        listIngredients.get(1).shouldHave(Condition.visible);
        listIngredients.get(2).shouldHave(Condition.visible);
        return this;
    }

    @Step("Проверить наличие заголовка Булки")
    public MainPage bunsHeaderIsDisplayed() {
        bunsHeader.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить наличие заголовка Соусы")
    public MainPage saucesHeaderIsDisplayed() {
        saucesHeader.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить наличие заголовка Начинки")
    public MainPage fillingsHeaderIsDisplayed() {
        fillingsHeader.shouldBe(Condition.visible);
        return this;
    }
}
