package com.geekbrains.hw6;

import com.geekbrains.hw6.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationExperimentalTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void  initDriver(){
        driver = new ChromeDriver();

    }

    @Test
    void ProcessCheckOutTest(){
        driver.get("http://automationpractice.com/");

        new MainPage(driver)
                .clickSignInButton()
                .login("test@test.tt", "qaz12345")
                .hoverWomenLineBlock()
                .goToEveningDresses()
                .goToPageProduct("Dress")
                .changeColor()
                .addToCart()
                .clickProceed()
                .clickSummaryButton()
                .clickSubmitAdressButton()
                .checkboxAgreeClick()
                .clickSubmitAdressButton()
                .payBankwire()
                .finishProcess();

        Assertions.assertEquals(driver.findElement(By.xpath("//*[contains(@class,'cheque-indent')]/*")).getText(),"Your order on My Store is complete.");

    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}
