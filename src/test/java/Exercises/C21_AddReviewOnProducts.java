package Exercises;

import com.github.javafaker.Faker;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C21_AddReviewOnProducts extends TestBase {

    @Test
    public void test21() {

        Actions actions = new Actions(driver);
        Faker faker = new Faker();

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
        assertEquals(expectedAllProductsText,actualAllProductsText);

        // Click on 'View Product' button
        WebElement stylishDressWE = driver.findElement(By.xpath("//*[text()='Rs. 1500']"));
        actions.scrollToElement(stylishDressWE).perform();

        WebElement viewProductButton = driver.findElement(By.xpath("//i[@class='fa fa-plus-square']"));
        viewProductButton.click();

        // Verify 'Write Your Review' is visible
        WebElement writeYourReviewWE = driver.findElement(By.xpath("//*[text()='Write Your Review']"));
        Assert.assertTrue(writeYourReviewWE.isDisplayed());

        // Enter name, email and review
        WebElement subscriptionWE = driver.findElement(By.id("susbscribe_email"));
        actions.scrollToElement(subscriptionWE).perform();

        WebElement nameArea = driver.findElement(By.id("name"));
        nameArea.sendKeys(faker.name().firstName(), Keys.TAB,faker.internet().emailAddress(),Keys.TAB,faker.lorem().sentence(10));

        //  Click 'Submit' button
        WebElement submitButton = driver.findElement(By.id("button-review"));
        submitButton.click();

        // Verify success message 'Thank you for your review.'
        WebElement tyForReviewWE = driver.findElement(By.xpath("//*[text()='Thank you for your review.']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(tyForReviewWE));

        Assert.assertTrue(tyForReviewWE.isDisplayed());

        String expectedTyForReviewText = "Thank you for your review.";
        String actualTyForReviewText = tyForReviewWE.getText();
        Assert.assertEquals(expectedTyForReviewText,actualTyForReviewText);
    }
}
