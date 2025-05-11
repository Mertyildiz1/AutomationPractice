package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C08_VerifyAllProductsAndProductDetailPage extends TestBase {
    @Test
    public void test08() throws InterruptedException {
        Actions action = new Actions(driver);
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
        Assert.assertEquals(expectedTitle,actualTitle);

        // Click on 'Products' button
        WebElement productsButton = driver.findElement(By.cssSelector(".material-icons.card_travel"));
        productsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        String expectedAllProductsTitle = "Automation Exercise - All Products";
        String actualAllProductsTitle = driver.getTitle();
        Assert.assertEquals(expectedAllProductsTitle,actualAllProductsTitle);

        WebElement searchInputArea = driver.findElement(By.xpath("//input[@id='search_product']"));
        Assert.assertTrue(searchInputArea.isDisplayed());

        // The products list is visible
        WebElement allProductsText = driver.findElement(By.xpath("//*[.='All Products']"));
        Assert.assertTrue(allProductsText.isDisplayed());

        // Click on 'View Product' of first product
        WebElement viewProductButton = driver.findElement(By.xpath("(//i[@class='fa fa-plus-square'])[4]"));
        action.moveToElement(viewProductButton).perform();

        WebElement firstProductViewProductButton = driver.findElement(By.xpath("(//i[@class='fa fa-plus-square'])[1]"));
        firstProductViewProductButton.click();

        // User is landed to product detail page
        String actualProductDetailTitle = driver.getTitle();
        String expectedProductDetailsTitle = "Automation Exercise - Product Details";
        Assert.assertEquals(expectedProductDetailsTitle,actualProductDetailTitle);

        WebElement quantityArea = driver.findElement(By.xpath("//*[.='Quantity:']"));
        Assert.assertTrue(quantityArea.isDisplayed());

        //Verify that detail detail is visible: product name, category, price, availability, condition, brand
        WebElement productName = driver.findElement(By.xpath("//*[.='Blue Top']"));
        Assert.assertTrue(productName.isDisplayed());
        WebElement category = driver.findElement(By.xpath("//*[.='Category: Women > Tops']"));
        Assert.assertTrue(category.isDisplayed());
        WebElement price = driver.findElement(By.xpath("//*[.='Rs. 500']"));
        Assert.assertTrue(price.isDisplayed());
        WebElement availability = driver.findElement(By.xpath("//*[.='Availability:']"));
        Assert.assertTrue(availability.isDisplayed());
        WebElement condition = driver.findElement(By.xpath("//*[.='Condition:']"));
        Assert.assertTrue(condition.isDisplayed());
        WebElement brand = driver.findElement(By.xpath("//*[.='Brand:']"));
        Assert.assertTrue(condition.isDisplayed());
    }
}
