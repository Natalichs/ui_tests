package com.geekbrains.hw6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends StartView{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'color_pick selected')]")
    public WebElement selectedColor;

    @FindBy(xpath = "//*[contains(@id, 'color_to')]//a[not(contains(@class, 'selected'))]")
    public List<WebElement> notselectedColors;

    @FindBy(xpath = "//*[@id='add_to_cart']/button")
    public WebElement addToCartButton;

    public ProductPage changeColor(){
        notselectedColors.stream().findFirst().get().click();
        return this;
    }
    public AddPopup addToCart(){
        addToCartButton.click();
        return new AddPopup(driver);
    }

}
