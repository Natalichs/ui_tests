package com.geekbrains.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class AuthAddToCartAndPayTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notification");
        chromeOptions.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://automationpractice.com/");

        //Авторизация
        driver.findElement(By.cssSelector(".login")).click();
        driver.findElement(By.id("email")).sendKeys("test@test.tt");
        driver.findElement(By.id("passwd")).sendKeys("qaz12345");
        driver.findElement(By.id("SubmitLogin")).click();

        //Перейти на страницу товара
        Actions actions = new Actions(driver);
        WebElement womenDressSelector = driver.findElement(By.xpath("//a[@title='Women']"));
        actions.moveToElement(womenDressSelector);
        actions.perform();
        driver.findElement(By.xpath("//li[@class=\"sfHover\"]//a[@title='Evening Dresses']")).click();
        driver.findElement(By.xpath("//a[contains(@class, 'product_img')]")).click();

        //Сменить цвет
        driver.findElement(By.xpath("//*[contains(@id, 'color_to')]//a[not(contains(@class, 'selected'))]")).click();

        //Добавить в корзину
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".button-container a")).click();

        //Процесс оформления заказа
        driver.findElement(By.xpath("//a[contains(@class,'checkout')]")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".checker input")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".bankwire")).click();
        driver.findElement(By.xpath("//*[contains(text(),'confirm')]/..")).click();



        Thread.sleep(2000);
        driver.quit();
    }
}
