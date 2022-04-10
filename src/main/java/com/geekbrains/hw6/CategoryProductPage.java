package com.geekbrains.hw6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryProductPage extends StartView{
    public CategoryProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class, 'product_img')]")
    public List<WebElement> pageProduct;

    public ProductPage goToPageProduct(String name){
        pageProduct.stream().filter(s-> s.getAttribute("title").contains(name)).findFirst().get().click();
        return new ProductPage(driver);
    }


}
