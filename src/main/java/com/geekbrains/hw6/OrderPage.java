package com.geekbrains.hw6;

import io.qameta.allure.Step;
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

    @Step("кликнуть Proceed")
    public OrderPage clickSummaryButton(){
        summaryProceedButton.click();
        return this;
    }
    @Step("кликнуть Proceed")
    public OrderPage clickSubmitAdressButton(){
        submitAdressButton.click();
        return this;
    }
    @Step("Проставить чекбокс Agree")
    public OrderPage checkboxAgreeClick(){
        checkboxAgree.click();
        return this;
    }
    @Step("Выбрать способ оплаты BankWire")
    public OrderPage payBankwire(){
        bankwirePay.click();
        return this;
    }
    @Step("Завнршить оформление заказа")
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
