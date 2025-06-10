package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C09_SearchProduct extends TestBase {

    @Test
    public void test09() {

        Actions actions = new Actions(driver);

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        WebElement featuresItemsText = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(featuresItemsText));
        Assert.assertTrue(featuresItemsText.isDisplayed());

        String expectedTitle = "Automation Exercise";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        // Click on 'Products' button
        WebElement productsButton = driver.findElement(By.cssSelector(".material-icons.card_travel"));
        productsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        String expectedAllProductsTitle = "Automation Exercise - All Products";
        String actualAllProductsTitle = driver.getTitle();
        Assert.assertEquals(expectedAllProductsTitle, actualAllProductsTitle);

        WebElement searchInputArea = driver.findElement(By.xpath("//input[@id='search_product']"));
        Assert.assertTrue(searchInputArea.isDisplayed());

        //Enter product name in search input and click search button
        String productName = "Blue Top";
        searchInputArea.sendKeys(productName);
        WebElement searchButton = driver.findElement(By.id("submit_search"));
        searchButton.click();

        // Verify 'SEARCHED PRODUCTS' is visible
        WebElement searchedProducts = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(searchedProducts.isDisplayed());
        String expectedText = "SEARCHED PRODUCTS";
        Assert.assertEquals(expectedText, searchedProducts.getText());

        //Verify all the products related to search are visible
        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();

        WebElement productNameMoveTo = driver.findElement(By.xpath("(//p)[3]"));
        Assert.assertTrue(productNameMoveTo.isDisplayed());
        Assert.assertEquals(productName, productNameMoveTo.getText());
    }
}
