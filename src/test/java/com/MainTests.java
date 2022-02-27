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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@RunWith(Parameterized.class)
public class MainTests {
    private final String driver;
    private MainPage mainPage;

    public MainTests(String driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/" + driver);
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);

        mainPage = open(MainPage.URL, MainPage.class);
    }

    @Parameterized.Parameters
    public static Object[] setDriver() {
        return new Object[][] {
                {"yandexdriver"},
                {"chromedriver"}
        };
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    public void testClickOnBunsTab() {
        mainPage.clickBunsTab()
                .listOfIngredientsIsDisplayed()
                .bunsHeaderIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void testClickOnSaucesTab() {
        mainPage.clickSaucesTab()
                .listOfIngredientsIsDisplayed()
                .saucesHeaderIsDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void testClickOnFillingsTab() {
        mainPage.clickFillingsTab()
                .listOfIngredientsIsDisplayed()
                .fillingsHeaderIsDisplayed();
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }
}
