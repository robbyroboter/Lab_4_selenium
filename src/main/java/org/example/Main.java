package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Установка пути к SafariDriver
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

        // Создание экземпляра WebDriver
        WebDriver driver = new SafariDriver();

        // Переход на главную страницу сайта Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Метод 1: по тексту ссылки
        WebElement contentsLink = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        contentsLink.click();

        Thread.sleep(2000);
        // Клик по ссылке "Current events"
        WebElement currentEventsLink = driver.findElement(By.linkText("Current events"));
        currentEventsLink.click();

        Thread.sleep(2000);

        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Метод 2: по частичному тексту ссылки с использованием частичного селектора
        contentsLink = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        contentsLink.click();

        Thread.sleep(2000);

        WebElement currentEventsLinkByPartialText = driver.findElement(By.partialLinkText("Current"));
        currentEventsLinkByPartialText.click();

        Thread.sleep(1000);

        driver.quit();
    }
}