package com.geekbrains.hw6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends StartView{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "passwd")
    public WebElement passwField;

    @FindBy(id = "SubmitLogin")
    public WebElement submitButton;

    @Step("Логин")
    public MyAccountPage login(String email, String passw){
        emailField.sendKeys(email);
        passwField.sendKeys(passw);
        submitButton.click();
        return new MyAccountPage(driver);
    }

}
