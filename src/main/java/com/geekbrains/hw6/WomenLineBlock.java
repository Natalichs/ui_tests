package com.geekbrains.hw6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenLineBlock extends StartView{
    public WomenLineBlock(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath = "//li[@class=\"sfHover\"]//a[@title='Evening Dresses']")
    public WebElement eveningDresses;

    public CategoryProductPage goToEveningDresses(){
        eveningDresses.click();
        return new CategoryProductPage(driver);
    }
}
