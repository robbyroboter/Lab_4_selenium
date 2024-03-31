package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;

public class WikipediaTest {

    @Parameters({"pageCount"})
    @Test
    public void testRandomPages(int pageCount) throws InterruptedException {

        // Создаем объект Random для генерации случайного числа
        Random random = new Random();

        // Создание экземпляра WebDriver
        WebDriver driver = new SafariDriver();

        // Открытие главной страницы Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Переход на указанное количество случайных страниц
        for (int i = 0; i < pageCount; i++) {
            // Поиск элементов являющиеся ссылками
            List<WebElement> selectors = driver.findElements(By.xpath("//a[@href]"));

            // Получаем случайный индекс от 0 до длины списка
            int randomIndex = random.nextInt(selectors.size());
            // Получаем случайный элемент из списка
            WebElement randomElement = selectors.get(randomIndex);

            Thread.sleep(1000);

            // Переходим по ссылке
            randomElement.click();

            // Получение URL текущей страницы
            String url = driver.getCurrentUrl();

            // Получение заголовка текущей страницы
            String title = driver.getTitle();

            // Получение списка источников (ссылок) на текущей странице
            List<WebElement> sources = driver.findElements(By.xpath("//a[@class='external text']"));
            //List<WebElement> sources = driver.findElements(By.cssSelector("a.reference"));
            //List<WebElement> sources = driver.findElements(By.cssSelector("a[class='external text']"));

            // Вывод информации о текущей странице
            System.out.println("URL: " + url);
            System.out.println("Title: " + title);
            System.out.println("Sources:");
            for (WebElement source : sources) {
                System.out.println("- " + source.getAttribute("href"));
            }
            System.out.println();

            // Возвращение на главную страницу Wikipedia
            driver.navigate().back();
            driver.get("https://en.wikipedia.org/wiki/Main_Page");
        }

        // Закрытие браузера
        driver.quit();
    }
}
