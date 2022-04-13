package com.geekbrains.hw6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends StartView{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".login")
    public WebElement signInButton;

    @Step("Кликнуть по кнопке Войти")
    public LoginPage clickSignInButton(){
        signInButton.click();
        return new LoginPage(driver);
    }

}
