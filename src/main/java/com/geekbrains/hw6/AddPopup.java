package com.geekbrains.hw6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPopup extends StartView{
    public AddPopup(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".button-container a")
    public WebElement ProceedAccess;

    @Step("Кликнуть Продолжить после добавления товара в корзину")
    public OrderPage clickProceed(){
        webDriverWait.until(ExpectedConditions.visibilityOf(ProceedAccess));
        ProceedAccess.click();
        return new OrderPage(driver);

    }
}
