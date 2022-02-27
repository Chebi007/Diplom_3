package com;

import com.pageobject.ForgotPasswordPage;
import com.pageobject.MainPage;
import com.pageobject.RegistrationPage;
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
public class LoginTests {
    private final String driver;
    private MainPage mainPage;
    private UserOperations userOperations;
    private String email;
    private String password;

    public LoginTests(String driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/" + driver);
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        userOperations = new UserOperations();
        Map<String, String>  response = userOperations.register();
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginFromMainPage() {
         mainPage = open(MainPage.URL, MainPage.class);
         mainPage.loginFromMainPage(email, password)
                 .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void testLoginFromAccountButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickAccountButtonLogin()
                .login(email, password)
                .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginFromRegistrationPage() {
        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.clickLoginLink()
                        .login(email, password)
                        .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = open(ForgotPasswordPage.URL, ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginLink()
                          .login(email, password)
                          .checkIsMainPageOpen();
    }

    @After
    public void tearDown() {
        userOperations.delete();
        webdriver().driver().close();
    }
}
