package com.geekbrains.hw6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends StartView{
    public OrderPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[contains(@class,'checkout')]")
    public WebElement summaryProceedButton;

    @FindBy(xpath = "//button[contains(@name,'process')]")
    public WebElement submitAdressButton;

    @FindBy(css = ".checker input")
    public WebElement checkboxAgree;

    @FindBy(css = ".bankwire")
    public WebElement bankwirePay;

    @FindBy(xpath = "//*[contains(text(),'confirm')]/..")
    public WebElement finishSubmitButton;

    @FindBy(xpath = "//*[contains(@class,'cheque-indent')]/*")
    public WebElement checkOrder;

    public OrderPage clickSummaryButton(){
        summaryProceedButton.click();
        return this;
    }
    public OrderPage clickSubmitAdressButton(){
        submitAdressButton.click();
        return this;
    }
    public OrderPage checkboxAgreeClick(){
        checkboxAgree.click();
        return this;
    }
    public OrderPage payBankwire(){
        bankwirePay.click();
        return this;
    }
    public OrderPage finishProcess(){
        finishSubmitButton.click();
        return this;
    }
    public OrderPage allStepsProcessBankwireAuthUser(){
        summaryProceedButton.click();
        submitAdressButton.click();
        checkboxAgree.click();
        submitAdressButton.click();
        bankwirePay.click();
        finishSubmitButton.click();
        return this;

    }



}
