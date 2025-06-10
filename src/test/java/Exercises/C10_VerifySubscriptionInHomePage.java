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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class C10_VerifySubscriptionInHomePage extends TestBase {
    @Test
    public void test10() throws InterruptedException, AWTException {
        Faker faker = new Faker();
        Robot robot = new Robot();

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

        //Scroll down to footer
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);

        //Verify text 'SUBSCRIPTION'
        String actualSubText = driver.findElement(By.xpath("//*[.='Subscription']")).getText();
        String expectedSubText = "SUBSCRIPTION";
        Assert.assertEquals(expectedSubText, actualSubText);

        // Enter email address in input and click arrow button
        WebElement subsEmailInput = driver.findElement(By.id("susbscribe_email"));
        subsEmailInput.sendKeys(faker.internet().emailAddress());

        WebElement arrowButton = driver.findElement(By.id("subscribe"));
        arrowButton.click();

        // Verify success message 'You have been successfully subscribed!' is visible
        WebElement subsAlert = driver.findElement(By.xpath("//div[@class='alert-success alert']"));
        Assert.assertTrue(subsAlert.isDisplayed());

        String actualSubAlertText = subsAlert.getText();
        String expectedSubAlertText = "You have been successfully subscribed!";
        Assert.assertEquals(expectedSubAlertText, actualSubAlertText);
    }
}
