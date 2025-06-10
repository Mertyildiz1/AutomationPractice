package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C22_AddToCartFromRecommendedItems extends TestBase {

    @Test
    public void test22() {

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Scrool to bottom of page
        WebElement subscriptionWE = driver.findElement(By.xpath("//*[text()='Subscription']"));
        actions.scrollToElement(subscriptionWE).perform();

        // Verify 'RECOMMENDED ITEMS' are visible
        WebElement recommendedItemsWE = driver.findElement(By.xpath("//*[text()='recommended items']"));
        Assert.assertTrue(recommendedItemsWE.isDisplayed());

        String expectedRecommendedItemsText = "RECOMMENDED ITEMS";
        String actualRecommendedItemsText = recommendedItemsWE.getText();
        Assert.assertEquals(expectedRecommendedItemsText, actualRecommendedItemsText);

        // Click on 'Add To Cart' on Recommended product
        WebElement menTshirtWE = driver.findElement(By.xpath("(//a[@data-product-id='2'])[3]"));
        wait.until(ExpectedConditions.elementToBeClickable(menTshirtWE));
        menTshirtWE.click();

        // Click on 'View Cart' button
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        wait.until(ExpectedConditions.visibilityOf(viewCartButton));
        viewCartButton.click();

        // Verify that product is displayed in cart page
        WebElement itemInCart = driver.findElement(By.xpath("//*[text()='Item']"));
        Assert.assertTrue(itemInCart.isDisplayed());

        String expectedItemName = "Men Tshirt";
        String actualItemName = driver.findElement(By.xpath("//h4//a")).getText();
        Assert.assertEquals(expectedItemName, actualItemName);
    }
}
