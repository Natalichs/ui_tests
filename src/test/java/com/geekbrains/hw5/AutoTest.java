package com.geekbrains.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutoTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    ChromeOptions chromeOptions;
    Actions actions;
    Cookie cookie;
    private final static String BASE_URL="http://automationpractice.com/";
    private final static String cookieName = "PrestaShop-a30a9934ef476d11b6cc3c983616e364";
    private final static String cookieAuthValue = "w%2FKa4WdcU6S5h%2Fd94yXzuqQeNmgc%2Bmy1%2BkyCZlei%2FnRjR6RJWf%2Fi6i7jn" +
            "DXR7xzl7KD05CGJGTfUxfxfTl%2FSZDd9mPhRVSf2udHMmFW2DiTqF0FiZFM6Y0AksmWNv85MIp1xyzQU8xVEg1oS1xMUoU4mnr%2B%2Bmv" +
            "NvO2Bkny%2BkfvSkkcSK%2F5hGirfRslAyogiWVEow7UUnqunyKIj%2FwU74fk97dFtN1lQde8jS8J4d5qM8xLWwLzoFsFG504FUW%2FrfD" +
            "DHTL1tJDA19oMtD5T9GcxUnXgRso2pbCw0OAVeJXUC9lh1jDFcKcMSYAsQXz1KMIhflv3HDNJRAs0ec8wKnmld7JJbTrOGY0%2BV3Exg6gt" +
            "r7KZWFAs6bqKo9DhbWOK5P33uUxzI%2Fo38tAsDvmMsp3Q%3D%3D000301";



    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    void setupDriver(){
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notification");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        actions = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.get(BASE_URL);
    }
    @Test
    void AuthTest(){
        driver.findElement(By.cssSelector(".login")).click();
        driver.findElement(By.id("email")).sendKeys("test@test.tt");
        driver.findElement(By.id("passwd")).sendKeys("qaz12345");
        driver.findElement(By.id("SubmitLogin")).click();

        Assertions.assertEquals(driver.findElement(By.cssSelector(".logout")).isDisplayed(),true);
    }

    @Test
    void GoToPageGoodsTest(){

        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Women']")));
        actions.perform();
        driver.findElement(By.xpath("//li[@class=\"sfHover\"]//a[@title='Evening Dresses']")).click();
        driver.findElement(By.xpath("//a[contains(@class, 'product_img')]")).click();

        Assertions.assertEquals(driver.getCurrentUrl().contains("controller=product"),true);
    }

    @Test
    void ChangeColorTest() throws InterruptedException {

        driver.get(BASE_URL+"?id_product=4&controller=product");
        String firstColor = driver.findElement(By.xpath("//*[contains(@class,'color_pick selected')]")).getAttribute("name");
        driver.findElement(By.xpath("//*[contains(@id, 'color_to')]//a[not(contains(@class, 'selected'))]")).click();
        driver.navigate().refresh();
        String secondColor = driver.findElement(By.xpath("//*[contains(@class,'color_pick selected')]")).getAttribute("name");
        Assertions.assertEquals(firstColor.equals(secondColor),false);
    }

    @Test
    void AddToCartTest() throws InterruptedException {

        driver.get(BASE_URL+"?id_product=4&controller=product");

        driver.findElement(By.xpath("//*[@id='add_to_cart']/button")).click();
        WebElement addToCart = driver.findElement(By.cssSelector(".button-container a"));
        webDriverWait.until(ExpectedConditions.visibilityOf(addToCart));
        addToCart.click();
        Assertions.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'shopping_cart')]//*[contains(@class,'ajax_cart_quantity')]")).getText(),"1");

    }
    @Test
    //Не смогла осилить тест тк что -то странное с куками, не могу понять почему у меня их 2 становится если я удаляю до добавления
    void ProcessCheckout() throws InterruptedException {

        driver.get(BASE_URL+"?id_product=4&controller=product");

        driver.findElement(By.xpath("//*[@id='add_to_cart']/button")).click();
        WebElement addToCart = driver.findElement(By.cssSelector(".button-container a"));
        webDriverWait.until(ExpectedConditions.visibilityOf(addToCart));
        addToCart.click();

        cookie= new Cookie(cookieName,cookieAuthValue);
        driver.manage().deleteCookieNamed(cookieName);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        driver.findElement(By.xpath("//a[contains(@class,'checkout')]")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".checker input")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".bankwire")).click();
        driver.findElement(By.xpath("//*[contains(text(),'confirm')]/..")).click();

        Assertions.assertEquals(driver.findElement(By.xpath("//*[contains(@class,'cheque-indent')]/*")).getText(),"Your order on My Store is complete");

        Thread.sleep(2000);
    }

    @Test
    //Общий тест до добавления Assertions и разбивки
    void AuthAddToCartAndPayTest(){
        //Авторизация
        driver.findElement(By.cssSelector(".login")).click();
        driver.findElement(By.id("email")).sendKeys("test@test.tt");
        driver.findElement(By.id("passwd")).sendKeys("qaz12345");
        driver.findElement(By.id("SubmitLogin")).click();

        //Перейти на страницу товара
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Women']")));
        actions.perform();
        driver.findElement(By.xpath("//li[@class=\"sfHover\"]//a[@title='Evening Dresses']")).click();
        driver.findElement(By.xpath("//a[contains(@class, 'product_img')]")).click();

        //Сменить цвет
        driver.findElement(By.xpath("//*[contains(@id, 'color_to')]//a[not(contains(@class, 'selected'))]")).click();

        //Добавить в корзину
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
        WebElement addToCart = driver.findElement(By.cssSelector(".button-container a"));
        webDriverWait.until(ExpectedConditions.visibilityOf(addToCart));
        addToCart.click();

        //Процесс оформления заказа
        driver.findElement(By.xpath("//a[contains(@class,'checkout')]")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".checker input")).click();
        driver.findElement(By.xpath("//button[contains(@name,'process')]")).click();
        driver.findElement(By.cssSelector(".bankwire")).click();
        driver.findElement(By.xpath("//*[contains(text(),'confirm')]/..")).click();

    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

}
