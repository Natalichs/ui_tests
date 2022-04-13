package com.geekbrains.hw6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends StartView{
    public MyAccountPage(WebDriver driver) {
        super(driver);
        womenLineBlock = new WomenLineBlock(driver);
    }
    public WomenLineBlock womenLineBlock;

    @FindBy(xpath = "//a[@title='Women']")
    public WebElement womenElement;

    @Step("Навести курсор на панель Women")
    public WomenLineBlock hoverWomenLineBlock(){
        actions.moveToElement(womenElement).build().perform();
        return womenLineBlock;
    }



}
