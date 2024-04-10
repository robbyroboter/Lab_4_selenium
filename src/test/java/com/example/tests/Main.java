package com.example.tests;

import com.example.pageobjects.WikipediaHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    private WebDriver driver;
    private WikipediaHomePage wikipediaHomePage;

    @BeforeTest
    public void setUp() {
        Properties properties = new Properties();
        try {
            // Загружаем файл config.properties
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Получаем значение пути к chromedriver из config.properties
        String chromedriverPath = properties.getProperty("chromedriver.path");

        // Устанавливаем путь к chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        // Создание экземпляра WebDriver
        driver = new ChromeDriver();

        // Инициализация объекта страницы Wikipedia
        wikipediaHomePage = new WikipediaHomePage(driver);
    }

    @Test(priority = 1)
    public void testNavigateToCurrentEvents() {
        // Переход на главную страницу сайта Wikipedia
        wikipediaHomePage.goToHomePage();

        // Клик по ссылке "Contents"
        wikipediaHomePage.clickContentsLink();

        // Клик по ссылке "Current events"
        wikipediaHomePage.clickCurrentEventsLink();
    }

    @Test(priority = 2)
    public void testNavigateToCurrentEventsByPartialText() {
        // Переход на главную страницу сайта Wikipedia
        wikipediaHomePage.goToHomePage();

        // Клик по ссылке "Contents"
        wikipediaHomePage.clickContentsLink();

        // Клик по ссылке "Current events" по частичному тексту
        wikipediaHomePage.clickCurrentEventsLinkByPartialText();
    }

    @AfterTest
    public void tearDown() {
        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}
