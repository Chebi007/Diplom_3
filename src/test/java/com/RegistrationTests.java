package com;

import com.model.User;
import com.pageobject.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@RunWith(Parameterized.class)
public class RegistrationTests {
    private final String driver;
    public static final String EMAIL_POSTFIX = "@yandex.ru";
    private RegistrationPage registrationPage;
    private UserOperations userOperations;
    private User user;

    public RegistrationTests(String driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/" + driver);
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(10));
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX);
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        userOperations = new UserOperations();
    }

    @Parameterized.Parameters
    public static Object[] setDriver() {
        return new Object[][] {
                {"yandexdriver"},
                {"chromedriver"}
        };
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void testRegistration() {
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword())
                        .login(user.getEmail(), user.getPassword())
                        .checkIsMainPageOpen();
    }

    @Test
    @DisplayName("Регистрация не проходит при вводе некорректного пароля")
    public void testFailedRegistration() {
        registrationPage.register(user.getName(), user.getEmail(),"123as");
        registrationPage.isIncorrectPasswordMessageDisplayed();
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }
}



