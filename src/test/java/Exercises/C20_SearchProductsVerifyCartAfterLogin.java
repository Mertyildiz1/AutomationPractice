package Exercises;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import static org.junit.Assert.*;

public class C20_SearchProductsVerifyCartAfterLogin extends TestBase {

    @Test
    public void test20() {

        Actions actions = new Actions(driver);

        // Navigate to url 'http://automationexercise.com'
        String url = "http://automationexercise.com";
        driver.get(url);

        // Click on 'Products' button
        WebElement productsButton = driver.findElement(By.xpath("//i[@class='material-icons card_travel']"));
        productsButton.click();

        // Verify user is navigated to ALL PRODUCTS page successfully
        WebElement allProductsWE = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        assertTrue(allProductsWE.isDisplayed());

        String expectedAllProductsText = "ALL PRODUCTS";
        String actualAllProductsText = allProductsWE.getText();
        assertEquals(expectedAllProductsText, actualAllProductsText);

        // Enter product name in search input and click search button
        WebElement searchInput = driver.findElement(By.id("search_product"));
        searchInput.sendKeys("Blue Top", Keys.TAB, Keys.ENTER);

        // Verify 'SEARCHED PRODUCTS' is visible
        WebElement searchedProducts = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        assertTrue(searchedProducts.isDisplayed());

        String expectedSearchedText = "SEARCHED PRODUCTS";
        String actualSearchedText = searchedProducts.getText();
        assertEquals(expectedSearchedText, actualSearchedText);

        // Verify all the products related to search are visible
        WebElement brandBıba = driver.findElement(By.xpath("(//span[@class='pull-right'])[8]"));
        actions.scrollToElement(brandBıba).perform();
        WebElement blueTopText = driver.findElement(By.xpath("(//*[text()='Blue Top'])[1]"));
        assertTrue(blueTopText.isDisplayed());

        // Add those products to cart
        WebElement addToCart = driver.findElement(By.xpath("//*[text()='Add to cart']"));
        addToCart.click();

        // Click 'Cart' button and verify that products are visible in cart
        WebElement viewCartButton = driver.findElement(By.xpath("//*[text()='View Cart']"));
        viewCartButton.click();

        WebElement blueTopWEOnCart = driver.findElement(By.xpath("//h4//a"));
        assertTrue(blueTopWEOnCart.isDisplayed());

        String expectedBlueTopTextOnCart = "Blue Top";
        String actualBlueTopTextOnCart = blueTopWEOnCart.getText();
        assertEquals(expectedBlueTopTextOnCart, actualBlueTopTextOnCart);

        // Click 'Signup / Login' button and submit login details
        WebElement signupLoginButton = driver.findElement(By.xpath("//*[text()=' Signup / Login']"));
        signupLoginButton.click();

        WebElement emailAdressInput = driver.findElement(By.name("email"));
        emailAdressInput.sendKeys("1yildizmert@gmail.com", Keys.TAB, "test123456", Keys.TAB, Keys.ENTER);

        // Again, go to Cart page
        WebElement cartButton = driver.findElement(By.xpath("//*[text()=' Cart']"));
        cartButton.click();
        // Verify that those products are visible in cart after login as well
        blueTopWEOnCart = driver.findElement(By.xpath("//h4//a"));
        assertTrue(blueTopWEOnCart.isDisplayed());
        assertEquals(expectedBlueTopTextOnCart, actualBlueTopTextOnCart);
    }
}
