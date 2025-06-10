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

public class C17_RemoveProductsFromCart extends TestBase {

    @Test
    public void test17() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Verify that home page is visible successfully
        String pageExpectedTitle = "Automation Exercise";
        String pageActualTitle = driver.getTitle();
        Assert.assertEquals(pageExpectedTitle, pageActualTitle);

        WebElement homePageTestCasesButton = driver.findElement(By.xpath("//*[.=' Test Cases']"));
        Assert.assertTrue(homePageTestCasesButton.isDisplayed());

        // Add products to cart
        WebElement productsButton = driver.findElement(By.xpath("//i[@class='material-icons card_travel']"));
        productsButton.click();

        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();

        WebElement blueTopAddToCartButton = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
        blueTopAddToCartButton.click();

        // Click 'Cart' button
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        viewCartButton.click();

        // Verify that cart page is displayed
        WebElement shoppingCartText = driver.findElement(By.xpath("//*[.='Shopping Cart']"));
        Assert.assertTrue(shoppingCartText.isDisplayed());

        // Click 'X' button corresponding to particular product
        WebElement xButton = driver.findElement(By.xpath("//a[@class='cart_quantity_delete']"));
        xButton.click();

        // Verify that product is removed from the cart
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//tr[@class='cart_menu']")));
        WebElement cartIsEmptyText = driver.findElement(By.xpath("//span//p"));
        String expectedCartEmptyText = "Cart is empty! Click here to buy products";
        Assert.assertTrue(cartIsEmptyText.isDisplayed());
    }
}
