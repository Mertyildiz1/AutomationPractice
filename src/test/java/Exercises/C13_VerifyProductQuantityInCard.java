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

public class C13_VerifyProductQuantityInCard extends TestBase {
    @Test
    public void test13() {

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
        Assert.assertEquals(expectedTitle,actualTitle);

        // Click 'View Product' for any product on home page
        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();
        WebElement blueTopViewButton = driver.findElement(By.xpath("(//*[text()='View Product'])[1]"));
        blueTopViewButton.click();

        // Increase quantity to 4
        WebElement quantityArea = driver.findElement(By.id("quantity"));
        quantityArea.click();
        quantityArea.sendKeys(Keys.ARROW_UP,Keys.ARROW_UP,Keys.ARROW_UP);

        // Click 'Add to cart' button
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='btn btn-default cart']"));
        addToCartButton.click();

        // Click 'View Cart' button
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        wait.until(ExpectedConditions.visibilityOf(viewCartButton));
        viewCartButton.click();

        // Verify that product is displayed in cart page with exact quantity
        WebElement quantityInCart = driver.findElement(By.xpath("//button[@class='disabled']"));
        String actualQuantity = quantityInCart.getText();
        String expectedQuantity = "4";
        Assert.assertEquals(expectedQuantity,actualQuantity);
    }
}
