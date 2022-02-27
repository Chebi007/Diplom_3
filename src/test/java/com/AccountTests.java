package com;

import com.pageobject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@RunWith(Parameterized.class)
public class AccountTests {
    private final String driver;
    private MainPage mainPage;
    private UserOperations userOperations;
    private String name;
    private String email;
    private String password;

    public AccountTests(String driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/" + driver);
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        userOperations = new UserOperations();
        Map<String, String> response = userOperations.register();
        name = response.get("name");
        email = response.get("email");
        password =  response.get("password");
    }

    @Parameterized.Parameters
    public static Object[] setDriver() {
        return new Object[][] {
                {"yandexdriver"},
                {"chromedriver"}
        };
    }

    @Test
    @DisplayName("Проверка перехода по клику на Личный кабинет")
    public void testClickOnAccountButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.loginFromMainPage(email, password)
                .clickAccountButton()
                .checkIsAccountPageOpen(name);
    }

    @Test
    @DisplayName("Проверка перехода по клику на Конструктор из Личного кабинета")
    public void testFromAccountToConstructor() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.loginFromMainPage(email, password)
                .clickAccountButton();
        mainPage.clickConstructorLink()
                .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers из Личного кабинета")
    public void testFromAccountToLogo() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.loginFromMainPage(email, password)
                .clickAccountButton();
        mainPage.clickStellarBurgersLogo()
                .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке Выйти в Личном кабинете")
    public void testPrivateAccountExit() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.loginFromMainPage(email, password)
                .clickAccountButton()
                .clickExitButton()
                .checkIsUserLogout();
    }

    @After
    public void tearDown() {
        userOperations.delete();
        webdriver().driver().close();
    }
}
