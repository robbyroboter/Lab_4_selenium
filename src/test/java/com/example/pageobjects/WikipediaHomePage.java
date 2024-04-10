package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WikipediaHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Конструктор класса
    public WikipediaHomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.driver = driver;
    }

    // Метод для перехода на главную страницу Wikipedia
    public void goToHomePage() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public void clickContentsLink() {
        WebElement contentsLink = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        contentsLink.click();
    }

    public void clickCurrentEventsLink() {
        WebElement currentEventsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Current events")));
        currentEventsLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
    }

    public void clickCurrentEventsLinkByPartialText() {
        WebElement currentEventsLinkByPartialText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Current")));
        currentEventsLinkByPartialText.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
    }

    public void clickRandomArticleLink() {
        WebElement randomArticleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Random article")));
        randomArticleLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
    }

    public void sources() {
        // Получение URL текущей страницы
        String url = driver.getCurrentUrl();

        // Получение заголовка текущей страницы
        String title = driver.getTitle();

        WebElement source = driver.findElement(By.id("References"));
        // Получение списка источников (ссылок) на текущей странице
        List<WebElement> sources = source.findElements(By.xpath("//a[@class='external text']"));
        //List<WebElement> sources = driver.findElements(By.cssSelector("a.reference"));
        //List<WebElement> sources = driver.findElements(By.cssSelector("a[class='external text']"));

        // Вывод информации о текущей странице
        System.out.println("URL: " + url);
        System.out.println("Title: " + title);
        System.out.println("Sources:");
        for (WebElement e : sources) {
            System.out.println("- " + e.getAttribute("href"));
        }
        System.out.println();

        // Возвращение на главную страницу Wikipedia
        driver.navigate().back();
    }
}
